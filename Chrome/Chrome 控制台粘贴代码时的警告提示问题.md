

这张图片展示了 **Chrome 浏览器开发者工具（DevTools）的控制台界面**，包含以下关键内容：  

### 2. 安全警告提示  
界面中部有一个**黄色背景的警告框**，内容为：  
> *“Warning: Don’t paste code into the DevTools Console that you don’t understand or haven’t reviewed yourself. This could allow attackers to steal your identity or take control of your computer. Please type ‘allow pasting’ below and hit Enter to allow pasting.”*  
（翻译：警告：不要将你不理解或未自行审查的代码粘贴到 DevTools 控制台中。这可能会让攻击者窃取你的身份或控制你的电脑。请在下方输入 “allow pasting” 并按回车键以允许粘贴。）  


要解决 Chrome 控制台粘贴代码时的警告提示问题，需**在控制台输入 `allow pasting` 并按回车键**，即可解除粘贴限制。  
### 原因解释：  
Chrome 开发者工具（DevTools）出于安全考虑，默认会阻止“未审查的代码粘贴”（防止恶意脚本注入）。当你在控制台粘贴代码时，系统会弹出该警告，要求你主动确认允许粘贴，以降低安全风险。  
操作步骤：  
1. 在控制台输入框中输入 `allow pasting`；  
2. 按下回车键，之后就能正常粘贴并执行代码啦～

