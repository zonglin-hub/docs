全球前三大指令集：X86、Arm、RISC-V。
X86指令集能够占领一半以上的PC处理器市场和95%以上的服务器市场，Arm指令集则占据了全球99%以上的移动设备。
RISC-V天生就带着三大BUFF：开源开放、免费、可修改。
开源的RISC-V带来了新的商业模式和商业机会。
RISC-V国际基金会22位高级会员中有12名来自中国。在国际标准建设中，阿里巴巴领导了其中的13个主要技术小组，是公认的投入力量最大的中国机构。
谷歌依然想独霸RISC-V操作系统市场
在上一波移动智能大潮中，ARM架构+安卓系统成为最大的赢家。根据知名数据咨询机构Strategy Analytics数据统计，截止目前为止，安卓系统全球市场份额约为80%，IOS系统全球市场份额约为18%，鸿蒙系统全球市场份额约为2%。
而在ARM架构背后，目前RISC-V架构发展的态势迅猛，比当年ARM架构的速度更快，并得到了产业界的广泛支持。在移动智能潮流中吃到红利的谷歌目前正在致力于成为RISC-V移动设备操作系统市场的霸主，并且谷歌采取了多项措施。
2023年伊始，谷歌就官宣，未来安卓操作系统将支持RISC-V指令集架构。谷歌Android工程总监Lars Bergstrom表示：他希望RISC-V被视为Android中的“一级平台”。
谷歌也在尝试打造一个开源的操作系统和平台，以满足RISC-V处理器在开源生态的发展。这个平台被命名为 KataOS，利用了RISC-V和谷歌的硬件信任根OpenTitan。在KataOS里，谷歌与Antmicro合作开发了用于嵌入式硬件设计的Renode模拟器，允许快速的软件/硬件设计并提供多核 RISC-V 平台。虽然KataOS还处于早期阶段，不过谷歌已经将其开源到GitHub论坛上，并提供一个用于证明 KataOS 的参考嵌入式平台Sparrow。
能够看出，谷歌在RISC-V操作系统方面有巨大的野心，想要通吃商用和开源RISC-V处理器市场。当然，有了安卓在前，谷歌完成这样的壮举也并不让人太意外。
RISC-V 成为 Debian 官方支持架构意味着什么？
Debian基础架构中官方已经成功完成了RISC-V 64位(riscv64)的移植。虽然现阶段Debian 12版本尚未支持RISC-V 64位架构，不过Debian团队已经表示在Debian 13 "Trixie"中将进一步完善该支持，并提供官方的RISC-V支持。特别需要指出的是，Debian是最古老的GNU/Linux发行版之一，适用于服务器、工作站和桌面电脑等要求稳定的嵌入式设备。
Debian 官方支持的架构中(除 AMD64外)，其他架构的移植大部分是商业公司主导、社区为辅的方式进行移植及维护。Debian RISC-V 的移植则是按照社区主导、RISC-V 硬件制造商提供硬件的模式进行，且很多 Debian 包的维护者会在第一时间合入对 riscv64的支持或者提供帮助，这就是开源指令集的优势所在，这也证明了 RISC-V 模式的成功，为 RISC-V 成为继 X86、ARM 的第三极指令集乃至超越 X86、ARM 奠定了基础 。
在移植过程中，Debian riscv64 port team 明显感受到 RISC-V 硬件制造商对 Debian 操作系统的偏爱，很多 RISC-V 硬件都是首先基于 Debian 操作操作系统进行适配并积极向 Debian 社区捐赠设备；反过来， riscv64成为 Debian 的官方架构，会吸引更多的 RISC-V 硬件制造商以 Debian 为原型进行开发。
和Debian一样，主流的Linux操作系统发行版Fedora、Gentoo、Ubuntu等几乎都在支持RISC-V的发展。就以Ubuntu来说，此前有报道称，Ubuntu已经支持国产厂商赛昉科技推出的赛昉VisionFive2。Canonical的硅联盟总监Gordan markusov指出，Ubuntu和VisionFive2能够让开发者更好地使用开源软件，更好地使用RISC-V。
在国内，华为的鸿蒙、openKylin、veket、RT-Thread和创维酷开系统、微器人系统等也已经都支持RISC-V架构。以华为鸿蒙来说，不仅是鸿蒙系统兼容RISC-V架构，同时华为也自己开发了RISC-V系统和RISC-V开发板。
市场上主流的操作系统基本都已经适配RISC-V处理器，这将让RISC-V落地到更广泛的领域中。
谷歌KataOS,Debian,ubuntu,Fedora,Gentoo等等皆已开始适配兼容RISC-V处理器,
国产操作系统方面鸿蒙，openKylin，veket，优麒麟，创维酷开 ，微器人系统等等皆已开始适配兼容RISC-V处理器.
