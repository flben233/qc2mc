# qc2mc
一个mirai插件，可以将指定QQ群的消息转发至Minecraft服务器，使用rcon协议

## 内容列表

- [使用说明](#使用说明)
- [徽章](#徽章)
- [相关仓库](#相关仓库)
- [如何贡献](#如何贡献)
- [使用许可](#使用许可)

## 使用说明

首先，你需要在Minecraft服务端 server.properties 文件中的对应内容
```
rcon.port= #rcon端口号，随便填
enable-rcon= #这里填写true以开启rcon
rcon.password= #rcon密码，随便填
```
本插件在首次启动时会在以下路径创建四个配置文件 ip,port,groupnumber,password四个文件，你需要依次填写 服务器IP,服务器rcon端口,需要转发的群号,服务器rcon密码
```
(mirai主目录)/config/org.shirakawatyu.qc2mc/
```
插件会在每次接收到消息时读取配置文件，如果配置文件为空或者填写不正确则会抛出异常


## 相关仓库

- [rkon-core](https://github.com/Kronos666/rkon-core) — 本插件的rcon使用了这个项目。

## 维护者

[@flben233](https://github.com/flben233)。

## 如何贡献

非常欢迎你的加入！[提一个 Issue](https://github.com/flben233/qc2mc/issues/new) 或者提交一个 Pull Request。

## 使用许可

[MIT](LICENSE) © flben233
