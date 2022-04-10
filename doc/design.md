# 实验二
## 部署
### 从项目工程中启动

在工程根目录下执行以下操作:

```
mvn verify
# 创建数据库。通过这种方式创建的数据库已经包含一些预置数据。
mvn exec:java -Dexec.mainClass=org.xidian.rpcdemo.server.jdbc.NewLib
# 运行服务器端
mvn exec:java -Dexec.mainClass=org.xidian.rpcdemo.server.MyLibGrpcServer
# 在另一个终端中运行客户端
mvn exec:java -Dexec.mainClass=org.xidian.rpcdemo.client.MyLibGrpcClient
```
### 通过jar包启动

```
# 请确保当前路径下已有数据库文件。你可以通过启动一次服务器端创建数据库文件
# {} 中的参数为可选项

# 启动服务器端
## 端口默认8080
java -jar demo-0.1.jar -s {your_port}

# 启动客户端
## 地址默认本机。端口默认 8080。
java -jar demo-0.1.jar -c {your_address} {your_port}
## 你可以使用如下命令，连接部署在本机，端口号为8080的服务器：
### java -jar demo-0.1.jar -c
### java -jar demo-0.1.jar -c 8080
### java -jar demo-0.1.jar -c 127.0.0.1 8080
### 注意：不能在指定地址的情况下，缺省端口
```
## 概述

```
├─.vscode
├─src
│  ├─test
│  │  ├─java
│  │  │  └─org
│  │  │      └─xidian
│  │  │          └─rpcdemo
│  │  └─proto
│  └─main
│      ├─java
│      │  └─org
│      │      └─xidian
│      │          └─rpcdemo
│      │              ├─server
│      │              │  ├─controller
│      │              │  ├─model
│      │              │  └─jdbc
│      │              └─client
│      │                  ├─controller
│      │                  ├─ui
│      │                  └─model
│      ├─proto
│      └─resources
├─doc
└─log
```

项目使用maven进行管理，主要程序放置在 src/main 中。

程序采用CS基础模型，服务器暴露对应接口，客户端通过命令行界面调用接口，客户端与服务器之间通过gRPC进行通信。

客户端，服务器遵循MVC设计。controller模块执行与数据相关的具体操作，model模块将数据抽象为book对象，ui模块负责用户交互与显示book对象，服务器端的jdbc模块连接数据库并进行一些相关操作。

proto储存gRPC的配置文件

项目使用slf4j作为日志框架，Logback作为日志实现，resources中存放了Logback的相关配置

项目使用sqlite作为数据持久化方案，便于部署与使用。

## gRPC

本项目使用gRPC在服务器与客户端之间进行通信。
### 配置
proto下的books.proto文件定义了通信的相关数据格式与方法

#### 消息定义

本项目中有4种消息类型：

BookID，书的编号，一个int类型的数

BookName，书名，一个字符串。用于模糊查询，在程序中可以是书名的一部分，也可以是作者名的一部分

Bookinfo，书籍信息，包含书籍编号，书名，作者名等相关信息。其中编号，书名的类型为前面定义的对应消息类型

Reply，回应，布尔类型，用于判断操作是否正确执行

#### 服务定义

项目定义了一个BookManager服务，该服务包含4个接口：

- add()，将书籍信息插入数据库，会返回操作执行状态

- queryByID()，根据书的编号查询相关图书，会返回图书信息

- queryByName()，通过名字模糊查找书籍，可以使用书名/作者名的部分进行查询。该接口为服务器端流接口，会返回一个书籍流，包含匹配到的多本图书

- delete()，根据书的编号删除对应书籍，会返回操作执行状态

### 服务器

服务器端的MyLibGrpcServer.java文件实现了相关的4个接口。

首先使用ServerBuilder构建并启动gRPC服务器，默认监听的接口为8080，用命令行启动时可通过相应参数指定

接口从请求中解析信息，调用controller模块在数据库中执行实际的CRUD操作，并给出对应的返回值
### 客户端

客户端controller模块中的Operator.java文件调用接口

Operator包含了对4个接口的调用与连接/断开gRPC的方法

## 服务器后端

数据持久化方案使用sqlite，sqlite作为一个轻量化的数据库，部署起来方便快捷，支持基本的sql操作。

### sqlite

项目在jdbc模块与controller模块中实现了对数据库的操作

jdbc模块中的NewLib.java将数据库还原为初始状态

jdbc模块中的Database.java通过java提供的jdbc接口实现了数据库的连接与关闭

controller模块Operator.java中定义了4种基本操作：

- add()，接受了一个书籍对象，将其添加到数据库中

- queryByID()，根据书的编号查询相关图书，返回一个书籍对象

- queryByName()，接受一个字符串，使用通配符查找bookname，author字段是否匹配，将查询到的结果存入一个book对象数组中，返回

- delete()，删除对应id的书籍信息

## 客户端界面

ui 模块使用了github上的jtext-table[1]项目(TextTable.java)，可以使用java输出文本表格，类似于mysql命令行的结果集表格

sampleUI.java接受book对象或者book对象数组，以表格的方式输出到终端

sampleMemu.java使用上述的表格工具实现了一个简单的菜单界面

## 其他

### 待完善部分

本项目中还有一些功能与模块将在后续版本进行完善：

- 服务器提供的接口不能满足所有需要，比如查询数据库中的所有书籍
- 书籍对象包含的属性不够充足，可以添加一个description字段，用以描述书籍信息
- 日志信息不够完善

### 计划的部分

本项目还将添加一些内容：

- 本地缓存，减少不必要的网路请求
- 用户身份验证，实现登录与注册服务
- 提升数据库的安全性，定义事务，完整性约束
- 模糊查询优化，提供功能更强大，可供自定义的搜索接口
- 完善多客户端并发访问
- gui界面，使用网页或者客户端gui程序显示界面
- 尝试将后端的关系数据库替换为对象-关系映射框架，进一步提升操作时的便捷性

### 附录

[1]:https://github.com/51gordon/jtext-table