# 在 Web 端执行 Linux 命令并显示结果

本文将介绍如何在 Web 端输入 Linux 命令，通过 WebSocket 发送到服务器后台执行，再将命令的反馈信息返回到前端显示。本文使用的技术栈是 Java 和 Spring Boot。

## WebSocket

WebSocket 是 HTML5 中新增的协议，使浏览器和服务器之间可以进行实时、双向的通信。与 HTTP 协议不同的是，WebSocket 在建立连接后，双方可以随时向对方发送数据，而不需要等待对方请求。这使得 WebSocket 在实时、高并发场景下表现更加优秀。

## SSHJ

SSHJ 是一个用于操作 SSH 协议的 Java 库。它可以连接到远程 SSH 服务器，并执行命令、上传下载文件等操作。

## 实现步骤

1. 创建一个 Spring Boot 项目，并添加 WebSocket 和 SSHJ 依赖。
2. 创建一个 WebSocket 处理器，用于处理连接建立、接收消息和发送消息等事件。在处理器中使用 SSHJ 连接到远程 SSH 服务器，并执行来自前端的命令。执行完成后，将结果发送给前端。
3. 配置 WebSocket 端点，在端点中注册 WebSocket 处理器。
4. 在前端页面中使用 JavaScript 创建 WebSocket 连接，发送命令并接收结果。

## Java 代码实现

以下是 Java 代码的实现，详见注释。

### WebSocket 处理器

```java
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ChannelExec;
import org.apache.sshd.client.config.hosts.DefaultKnownHostsRepository;
import org.apache.sshd.client.config.hosts.KnownHostsRepository;
import org.apache.sshd.client.keyverifier.ServerKeyVerifier;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.config.keys.KeyUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SSHWebSocketHandler extends TextWebSocketHandler {

    private SshClient sshClient;

    // 连接建立时触发
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 连接到 SSH 服务器
        sshClient = SshClient.setUpDefaultClient();
        sshClient.setServerKeyVerifier(ServerKeyVerifier.ACCEPT_ALL);
        sshClient.start();
        ClientSession sshSession = sshClient.connect("your_ssh_server_ip").await().getSession();
        sshSession.addPasswordIdentity("your_ssh_password");
        sshSession.auth().verify();

        // 将 SSH 会话保存在 WebSocket 会话中
        session.getAttributes().put("sshSession", sshSession);
    }

    // 接收到消息时触发
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String command = message.getPayload();

        // 创建 SSH 执行通道
        ChannelExec channel = sshClient.createChannel(ChannelExec.class);
        channel.setIn(null);
        channel.setCommand(command);

        // 执行命令并将输出保存到字符串中
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        channel.setOut(out);
        channel.open();
        channel.waitFor(ChannelExec.CHANNEL_CLOSED, 0);

        String result = new String(out.toByteArray(), StandardCharsets.UTF_8);

        // 将结果发送给客户端
        session.sendMessage(new TextMessage(result));
    }

    // 连接关闭时触发
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 关闭 SSH 会话和客户端
        ClientSession sshSession = (ClientSession) session.getAttributes().get("sshSession");
        sshSession.close();
        sshClient.stop();
    }
}
```

### WebSocket 配置

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private SSHWebSocketHandler sshWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(sshWebSocketHandler, "/ssh").setAllowedOrigins("*");
    }
}

```

### 前端页面

在前端页面中，使用 JavaScript 创建 WebSocket 连接，并发送命令和接收结果。以下是一个示例代码：

```html
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>执行 Linux 命令</title>
    <script>
        var socket = new WebSocket("ws://your_server_ip:8080/ssh");
        socket.onopen = function() {
            console.log("WebSocket opened");
        };
        socket.onmessage = function(event) {
            console.log("Received message: " + event.data);
            document.getElementById("result").innerText = event.data; // 在页面上显示命令执行结果
        };
        socket.onclose = function(event) {
            console.log("WebSocket closed: " + event.code + " " + event.reason);
        };
        function sendCommand() {
            var command = document.getElementById("command").value;
            console.log("Sending command: " + command);
            socket.send(command); // 发送命令到服务器
        }
    </script>
</head>
<body>
    <h1>执行 Linux 命令</h1>
    <input type="text" id="command" placeholder="输入 Linux 命令">
    <button onclick="sendCommand()">执行</button>
    <pre id="result"></pre>
</body>
</html>

```

### 运行应用

在终端中进入应用项目所在的目录，输入以下命令启动应用：

```sh
mvn spring-boot:run
```

在浏览器中访问应用地址，输入命令并点击发送按钮，即可执行命令并在页面上显示结果。

总结
本文介绍了如何在 Web 端输入 Linux 命令，通过 WebSocket 发送到服务器后台执行，再将命令的反馈信息返回到前端显示。本文使用的技术栈是 Java 和 Spring Boot，具体实现过程中还需要考虑安全性等问题。
