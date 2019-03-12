# iControl
Android - PC 简易控制程序（PC端程序）

使用Android，在同一局域网环境下，控制Windows鼠标键盘

## 运行方法
1. 本地回环测试
    1. 若需无GUI测试运行，请在取消main函数注释后运行MainBlock.java；若需进行完整测试，请执行UserInterface.java
    2. 运行AndroidSim.java，并在AndroidSim的控制台中输入指令表中所包含的指令，即可实现对代码的本地测试

2. Android - PC 运行
    1. 需要自行实现一个Android程序（原程序现已丢失），并实现局域网内的TCP连接以及字符传输
    2. 在Android端开启连接之前，运行UserInterface.java

具体字符串及对应操作请参阅 iControl_v2指令表.txt