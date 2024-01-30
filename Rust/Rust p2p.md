# p2p

<details><summary><b>[+] Expand description</b></summary>

p2p 简介

- P2P：peer-to-peer（点对点）
- P2P 是一种网络技术，可以在不同的计算机之间共享各种计算资源，如 CPU、网络带宽和存储。
- P2P 是当今用户在线共享文件（如音乐、图像和其他数字媒体）的一种非常常用的方法。
    - Bittorrent 和 Gnutella 是流行的文件共享 p2p 应用程序的例子。以及比特币和以太坊等区块链网络。
    - 它们不依赖中央服务器或中介来连接多个客户端。
    - 最重要的是，它们利用用户的计算机作为客户端和服务器，从而将计算从中央服务器上卸载下来。
- 传统的分布式系统使用 Client-Server 范式来部署
- P2P 是另一种分布式系统
    - 在 P2P 中，一组节点（或对等点，Peer）彼此直接交互以共同提供公共服务，而无需中央协调器或管理员
    - P2P 系统中的每个节点（或 Peer）都可以充当客户端（从其他节点请求信息）和服务器（存储/检索数据并响应客户端请求执行必要的计算）。
    - P2P 网络中的所有节点不必完全相同，一个关键特征将 Client-Server 网络与 P2P 网络区分开来：缺乏具有唯一权限的专用服务器。在开放、无许可的 P2P 网络中，任何节点都可以决定提供与 P2P 节点相关的全部或部分服务集。

P2P 的特点

- 与 Client-Server 网络相比，P2P 网络能够在其上构建不同类别的应用程序，这些应用程序是无许可、容错和抗审查的。
    - 无许可：因为数据和状态是跨多个节点复制的，所以没有服务器可以切断客户机对信息的访问。
    - 容错性：因为没有单点故障，例如中央服务器。
    - 抗审查：如区块链等网络。
    - P2P 计算还可以更好地利用资源。

P2P 的复杂性

- 构建 P2P 系统要比传统 Client-Server 的系统复杂
    - 传输：P2P 网络中的每个 Peer 都可以使用不同的协议，例如HTTP(s)、TCP、UDP等。
    - 身份：每个 Peer 都需要知道其想要连接并发送消息的 Peer 的身份。
    - 安全性：每个 Peer 都应该能够以安全的方式与其他 Peer 通信，而不存在第三方拦截或修改消息的风险等。
    - 路由：每个 Peer 可以通过各种路由（例如数据包在 IP 协议中的分布方式）从其他 Peer 接收消息，这意味着如果消息不是针对自身的，则每个 Peer 都应该能够将消息路由到其他 Peer。
    - 消息传递：P2P 网络应该能够发送点对点消息或组消息（以发布/订阅模式）。

P2P 的要求 - 传输

- TCP/IP 和 UDP 协议无处不在，在编写网络应用程序时非常流行。但还有其他更高级别的协议，如 HTTP（TCP上分层）和 QUIC（UDP上分层）。
- P2P 网络中的每个 Peer 都应该能够启动到另一个节点的连接，并且由于网络中 peer 的多样性，能够通过多个协议监听传入的连接。

P2P 的要求 - Peer 身份

- 与 web 开发领域不同，在 web 开发领域中，服务器由唯一的域名标识（例如 <www.rust-lang.org，然后使用域名服务将其解析为服务器的IP地址）>
- P2P 网络中的节点需要唯一身份，以便其他节点可以访问它们。
- P2P 网络中的节点使用公钥和私钥对（非对称公钥加密）与其他节点建立通信。
    - P2P 网络中的节点的身份称为 PeerId，是节点公钥的加密散列。

P2P 的要求 - 安全

- 加密密钥对和 PeerId 使节点能够与它的 peers 建立安全、经过身份验证的通信通道。但这只是安全的一个方面。
- 节点还需要实现授权框架，该框架为哪个节点可以执行何种操作建立规则。
- 还有需要解决的网络级安全威胁，如 sybil 攻击（其中一个节点运营商利用不同身份启动大量节点，以获得网络中的优势地位）或 eclipse 攻击（其中一组恶意节点共谋以特定节点为目标，使后者无法到达任何合法节点）。

P2P 的要求 - Peer 路由

- P2P 网络中的节点首先需要找到其他 peer 才能进行通信。这是通过维护 peer 路由表来实现的，该表包含对网络中其他 peer 的引用。
- 但是，在具有数千个或更多动态变化的节点（即节点加入和离开网络）的 P2P 网络中，任何单个节点都难以为网络中的所有节点维护完整而准确的路由表。Peer 路由使节点能够将不是给自己准备的消息路由到目标节点。

P2P 的要求 - 消息

- P2P 网络中的节点可以向特定节点发送消息，但也可以参与广播消息协议。
    - 例如，发布/订阅，其中节点注册对特定主题的兴趣（订阅），发送该主题消息的任何节点（发布）都由订阅该主题的所有节点接收。这种技术通常用于将消息的内容传输到整个网络。

P2P 的要求 - 流多路复用

- 流多路复用（Stream multiplexing）是通过公共通信链路发送多个信息流的一种方法。
- 在 P2P 的情况下，它允许多个独立的“逻辑”流共享一个公共 P2P 传输层。
    - 当考虑到一个节点与不同 peers 具有多个通信流的可能性，或者两个远程节点之间也可能存在多个并发连接的可能性时，这一点变得很重要。
    - 流多路复用有助于优化 peer 之间建立连接的开销。

注意：多路复用在后端服务开发中很常见，其中客户端可以与服务器建立底层网络连接，然后通过底层网络连接多路复用不同的流（每个流具有唯一的端口号）Libp2p

- libp2p 是一个由协议、规范和库组成的模块化系统，它支持 P2P 应用程序的开发。
- 它目前支持三种语言：JS、Go、Rust
    - 未来将支持 Haskell、Java、Python等
- 它被许多流行的项目使用，例如：IPFS、Filecoin 和 Polkadot 等。

Libp2p 的主要模块

- 传输（Transport）：负责从一个 peer 到另一个 peer 的数据的实际传输和接收
- 身份（Identity）：libp2p 使用公钥密钥（PKI）作为 peer 节点身份的基础。使用加密算法为每个节点生成唯一的 peer id。
- 安全（Security）：节点使用其私钥对消息进行签名。节点之间的传输连接可以升级为安全的加密通道，以便远程 peer 可以相互信任，并且没有第三方可以拦截它们之间的通信。
- Peer 发现（Peer Discovery）：允许 peer 在 libp2p 网络中查找并相互通信。
- Peer 路由（Peer Routing）：使用其他 peer 的知识信息来实现与 peer 节点的通信。
- 内容发现（Content Discovery）：在不知道哪个 peer 节点拥有该内容的情况下，允许 peer 节点从其他 peer 节点获取部分内容。
- 消息（Messaging）：其中发布/订阅：允许向对某个主题感兴趣的一组 peer 发送消息。

</details>

这里只是一个简单的 `Dome`

相关依赖

```tomls
[dependencies]
libp2p = "0.46.1"
tokio = { version = "1.19.2", features = ["full"] }
```

公钥和私钥

- 加密身份使用公钥基础设施（PKI），广泛用于为用户、设备和应用程序提供唯一身份，并保护端到端通信的安全。
- 它的工作原理是创建两个不同的加密密钥，也称为由私钥和公钥组成的密钥对，它们之间具有数学关系。
- 密钥对有着广泛的应用，但在 P2P 网络中
    - 节点使用密钥对彼此进行身份识别和身份验证。
    - 公钥可以在网络中与其他人共享，但决不能泄漏节点的私钥。

公钥和私钥的例子 - 访问传统的服务器

- 如果您想连接到数据中心的远程服务器（使用SSH），用户可以生成密钥对并在远程服务器上配置公钥，从而授予用户访问权限。
- 但远程服务器如何知道哪个用户是该公钥的所有者？
    - 为了实现这一点，当连接（通过SSH）到远程服务器时，用户必须指定私钥（与存储在服务器上的公钥关联的）。
    - 私钥从不发送到远程服务器，但SSH客户端（在本地服务器上运行）使用用户的私钥向远程SSH服务器进行身份验证。

这里需要安装 [Cmake](https://cmake.org/) 才可正常编译

```rust
use libp2p::{identity::Keypair, PeerId};

#[tokio::main]
async fn main() {
    let new_key = Keypair::generate_ed25519();        // 生成密钥对，密钥对的类型为 ed25519
    let new_peer_id = PeerId::from(new_key.public()); // 生成 PeerId

    // New Peer ID is PeerId("12D3KooWGgzCi5mozvnV2SNuFpZy13hgKVmvryM3wyADwi6PRiza")
    println!("New Peer ID is {:?}", new_peer_id);
}
```

多地址（Multiaddresses）

- 在 libp2p 中，peer 的身份在其整个生命周期内都是稳定且可验证的。
- 但 libp2p 区分了 peer 的身份和位置。
    - peer 的身份是 peer id。
- peer 的位置是可以到达对方的网络地址。
    - 例如，可以通过 TCP、websockets、QUIC 或任何其他协议访问 peer。
    - libp2p 将这些网络地址编码成一个自描述格式，它叫做 multiaddress（multiaddr）。
    - 因此，在 libp2p中，multiaddress 表示 peer 的位置。

多地址

- 当 p2p 网络上的节点共享其联系信息时，它们会发送一个保护网络地址和 peer id 的多地址（multiaddress）。
- 节点多地址的 peer id 表示如下：
    - /p2p/12D3KooWBu3fmjZgSmLkQ2p...
- 多地址的网络地址表示如下：
    - /ip4/192.158.1.23/tcp/1234
- 节点的完整地址就是 peer id 和网络地址的组合：
    - /ip4/192.158.1.23/tcp/1234/p2p/12D3KooWBu3fmjZgSmLkQ2p...

Swarm 和网络行为

- Swarm 是 libp2p 中给定 P2P 节点内的网络管理器模块。
- 它维护从给定节点到远程节点的所有活动和挂起连接，并管理已打开的所有子流的状态。

Swarm 的结构和上下文环境

- Swarm 代表了一个低级接口，并提供了对 libp2p 网络的细粒度控制。Swarm 是使用传输、网络行为和节点 peer id 的组合构建的。
- 传输（Transport）会指明如何在网络上发送字节，而网络行为会指明发送什么字节，发送给谁。
    - 多个网络行为可以与单个运行节点相关联。
- 需要注意的是，同一套代码在 libp2p 网络的所有节点上运行，这与客户端和服务器具有不同代码库的 Client-Server 模型不同。

```rust
use libp2p::futures::StreamExt;
use libp2p::swarm::{DummyBehaviour, Swarm, SwarmEvent};
use libp2p::{identity, PeerId};
use std::error::Error;

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    let new_key = identity::Keypair::generate_ed25519();
    let new_peer_id = PeerId::from(new_key.public());
    println!("New Peer ID is: {:?}", new_peer_id);
    // 创建 网络行为
    let behaviour = DummyBehaviour::default();
    // 使用密钥对创建传输
    let transport = libp2p::development_transport(new_key).await?;
    // 创建Swarm
    let mut swarm = Swarm::new(transport, behaviour, new_peer_id);
    //监听 ip4 端口为 0 随机选择一个端口
    swarm.listen_on("/ip4/0.0.0.0/tcp/0".parse()?)?;

    loop {
        match swarm.select_next_some().await {
            // 如果创建一个新的监听地址，打印输出
            SwarmEvent::NewListenAddr { address, .. } => {
                println!("Listening on local Address {:?}", address)
            }
            _ => {}
        }
    }
}
```

运行这段代码；你应该能看到如下输出：

```sh
PS D:\.github\async> cargo run --package async --bin async
    Finished dev [unoptimized + debuginfo] target(s) in 3.14s
     Running `target\debug\async.exe`
New Peer ID is: PeerId("12D3KooWGeYcxb4gq3Wf8dEX3fuCWtfXX5kNePa6RXXrhwX1ciLU")
Listening on local Address "/ip4/192.168.3.2/tcp/35297"
Listening on local Address "/ip4/127.0.0.1/tcp/35297"

----------------------------------------------------------------------------------------------------------
PS D:\.github\async> cargo run --package async --bin async
    Finished dev [unoptimized + debuginfo] target(s) in 0.33s
     Running `target\debug\async.exe`
New Peer ID is: PeerId("12D3KooWFuBpwdwpfWoEgoFvPmEFwcawq7A5oEWnUdjZ1BWSeDZy")
Listening on local Address "/ip4/192.168.3.2/tcp/35298"
Listening on local Address "/ip4/127.0.0.1/tcp/35298"
```

在 peer 节点之间交换 ping 命令

```rust
use libp2p::futures::StreamExt;
use libp2p::ping::{Ping, PingConfig};
use libp2p::swarm::{Swarm, SwarmEvent};
use libp2p::{identity, Multiaddr, PeerId};
use std::error::Error;

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    let new_key = identity::Keypair::generate_ed25519();
    let new_peer_id = PeerId::from(new_key.public());
    println!("New Peer ID is: {:?}", new_peer_id);

    let transport = libp2p::development_transport(new_key).await?; // 使用密钥对创建传输
    let behaviour = Ping::new(PingConfig::new().with_keep_alive(true)); // 创建 网络行为
    let mut swarm = Swarm::new(transport, behaviour, new_peer_id); // 创建Swarm
    swarm.listen_on("/ip4/0.0.0.0/tcp/0".parse()?)?;

    // 本地节点向远程节点发出连接  远程地址从命令行输入的参数取出
    if let Some(remote_peer) = std::env::args().nth(1) {
        let remote_peer_multiaddr: Multiaddr = remote_peer.parse()?;
        swarm.dial(remote_peer_multiaddr)?; // 拨号
        println!("Dialed remote peer: {:?}", remote_peer); // 打印远程地址
    }

    loop {
        match swarm.select_next_some().await {
            SwarmEvent::NewListenAddr { address, .. } => {
                println!("Listening on local Address {:?}", address)
            }
            // 当本地节点发送一个 Ping 消息的时候，远程节点会发送一个 Pong 的消息，这个事件被接收到，打印输出
            SwarmEvent::Behaviour(event) => println!("Event received from peer is {:?}", event),
            _ => {}
        }
    }
}
```

运行这段代码；你应该能看到如下输出：

```sh
PS D:\.github\async> cargo run --package async --bin async
   Compiling async v0.1.0 (D:\.github\async)
    Finished dev [unoptimized + debuginfo] target(s) in 4.86s
     Running `target\debug\async.exe`
New Peer ID is: PeerId("12D3KooWSD7Lr3orfbpJ91U8eDLprrMuDWFzhsWjzt8vtufWdXoy")
Listening on local Address "/ip4/192.168.3.2/tcp/35437"
Listening on local Address "/ip4/127.0.0.1/tcp/35437"
Event received from peer is Event { peer: PeerId("12D3KooWGguocpwfh2dWaDDX7cYio9kZhChUkimuNHxSBzTGAbbD"), result: Ok(Pong) }
Event received from peer is Event { peer: PeerId("12D3KooWGguocpwfh2dWaDDX7cYio9kZhChUkimuNHxSBzTGAbbD"), result: Ok(Ping { rtt: 342.6µs }) }
Event received from peer is Event { peer: PeerId("12D3KooWGguocpwfh2dWaDDX7cYio9kZhChUkimuNHxSBzTGAbbD"), result: Ok(Pong) }
Event received from peer is Event { peer: PeerId("12D3KooWGguocpwfh2dWaDDX7cYio9kZhChUkimuNHxSBzTGAbbD"), result: Ok
----------------------------------------------------------------------------------------------------------
PS D:\.github\async> cargo run --package async --bin async /ip4/192.168.3.2/tcp/35437
    Finished dev [unoptimized + debuginfo] target(s) in 0.31s
     Running `target\debug\async.exe /ip4/192.168.3.2/tcp/35437`
New Peer ID is: PeerId("12D3KooWGguocpwfh2dWaDDX7cYio9kZhChUkimuNHxSBzTGAbbD")
Dialed remote peer: "/ip4/192.168.3.2/tcp/35437"
Listening on local Address "/ip4/192.168.3.2/tcp/35445"
Listening on local Address "/ip4/127.0.0.1/tcp/35445"
Event received from peer is Event { peer: PeerId("12D3KooWSD7Lr3orfbpJ91U8eDLprrMuDWFzhsWjzt8vtufWdXoy"), result: Ok(Pong) }
Event received from peer is Event { peer: PeerId("12D3KooWSD7Lr3orfbpJ91U8eDLprrMuDWFzhsWjzt8vtufWdXoy"), result: Ok(Ping { rtt: 266.5µs }) }
Event received from peer is Event { peer: PeerId("12D3KooWSD7Lr3orfbpJ91U8eDLprrMuDWFzhsWjzt8vtufWdXoy"), result: Ok(Pong) }
Event received from peer is Event { peer: PeerId("12D3KooWSD7Lr3orfbpJ91U8eDLprrMuDWFzhsWjzt8vtufWdXoy"), result: Ok(Ping { rtt: 610.2µs }) }
```

发现 peer

- mDNS 是由 RFC 6762（datatracker.ietf.org/doc/html/rfc6762）定义的协议，它将主机名解析为 IP 地址。
    - 在 libp2p 中，它用于发现网络上的其他节点。
- 在 libp2p 中实现的网络行为 mDNS 将自动发现本地网络上的其他 libp2p 节点。

```rust
use libp2p::{
    futures::StreamExt,
    identity::Keypair,
    mdns::{Mdns, MdnsConfig, MdnsEvent},
    swarm::{Swarm, SwarmEvent},
    Multiaddr, PeerId,
};
use std::error::Error;

#[tokio::main]
async fn main() -> Result<(), Box<dyn Error>> {
    let id_keys = Keypair::generate_ed25519();
    let peer_id = PeerId::from(id_keys.public());
    println!("New Peer ID is {:?}", peer_id);
    let transport = libp2p::development_transport(id_keys).await?;
    let behaviour = Mdns::new(MdnsConfig::default()).await?; // 创建 Mdns
    let mut swarm = Swarm::new(transport, behaviour, peer_id);
    swarm.listen_on("/ip4/0.0.0.0/tcp/0".parse()?)?;
    if let Some(remote_peer) = std::env::args().nth(1) {
        let remote_peer_multiaddr: Multiaddr = remote_peer.parse()?;
        swarm.dial(remote_peer_multiaddr)?;
        println!("Dialed remote peer: {:?}", remote_peer);
    }

    loop {
        match swarm.select_next_some().await {
            SwarmEvent::NewListenAddr { address, .. } => {
                println!("Listening on Local Address {:#?}", address)
            }
            // 发现一个 peer，打印输出
            SwarmEvent::Behaviour(MdnsEvent::Discovered(peers)) => {
                for (peer, addr) in peers {
                    println!("discovered {} {}", peer, addr)
                }
            }
            // 过期了，打印输出
            SwarmEvent::Behaviour(MdnsEvent::Expired(expired)) => {
                for (peer, addr) in expired {
                    println!("expired {} {}", peer, addr)
                }
            }
            _ => {}
        }
    }
}
```

运行这段代码；你应该能看到如下输出：

```sh
PS D:\.github\rust> cargo run --package async --example p2p  # 启动第一个节点
    Finished dev [unoptimized + debuginfo] target(s) in 0.31s
     Running `target\debug\examples\p2p.exe`
New Peer ID is PeerId("12D3KooWPNDznECWGgqnw4tDPSHyoU536Le5EGaYc9iyQKq6byq4")
Listening on Local Address "/ip4/192.168.3.2/tcp/32549"
Listening on Local Address "/ip4/127.0.0.1/tcp/32549"
discovered 12D3KooWPqa48Tm5R5jFHpmiFYGGBEoikDMvhPiABHRAjVq8ZZSY /ip4/192.168.3.2/tcp/32513
discovered 12D3KooWPqa48Tm5R5jFHpmiFYGGBEoikDMvhPiABHRAjVq8ZZSY /ip4/127.0.0.1/tcp/32513

----------------------------------------------------------------------------------------------------------
PS D:\.github\rust> cargo run --package async --example p2p  # 启动第二个节点
    Finished dev [unoptimized + debuginfo] target(s) in 0.29s
     Running `target\debug\examples\p2p.exe`
New Peer ID is PeerId("12D3KooWQyHFiKKPtshX8hRpFf7mf1ujQu8crV5psiwjQ448hYhu")
Listening on Local Address "/ip4/192.168.3.2/tcp/32550"
Listening on Local Address "/ip4/127.0.0.1/tcp/32550"
discovered 12D3KooWPNDznECWGgqnw4tDPSHyoU536Le5EGaYc9iyQKq6byq4 /ip4/192.168.3.2/tcp/32549
discovered 12D3KooWPNDznECWGgqnw4tDPSHyoU536Le5EGaYc9iyQKq6byq4 /ip4/127.0.0.1/tcp/32549

----------------------------------------------------------------------------------------------------------
PS D:\.github\rust> cargo run --package async --example p2p  # 第一个节点监控
    Finished dev [unoptimized + debuginfo] target(s) in 0.31s
     Running `target\debug\examples\p2p.exe`
New Peer ID is PeerId("12D3KooWPNDznECWGgqnw4tDPSHyoU536Le5EGaYc9iyQKq6byq4")
Listening on Local Address "/ip4/192.168.3.2/tcp/32549"
Listening on Local Address "/ip4/127.0.0.1/tcp/32549"
discovered 12D3KooWPqa48Tm5R5jFHpmiFYGGBEoikDMvhPiABHRAjVq8ZZSY /ip4/192.168.3.2/tcp/32513
discovered 12D3KooWPqa48Tm5R5jFHpmiFYGGBEoikDMvhPiABHRAjVq8ZZSY /ip4/127.0.0.1/tcp/32513
discovered 12D3KooWQyHFiKKPtshX8hRpFf7mf1ujQu8crV5psiwjQ448hYhu /ip4/192.168.3.2/tcp/32550
discovered 12D3KooWQyHFiKKPtshX8hRpFf7mf1ujQu8crV5psiwjQ448hYhu /ip4/127.0.0.1/tcp/32550
```
