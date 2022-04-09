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
# {} 中的参数为可选参数

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

## gRPC

### 配置

### 服务器

### 客户端

## 服务器后端

### sqlite

## 客户端界面

## 其他