library with gRPC

# build

Run in this directory:

```
mvn verify
# creat db
mvn exec:java -Dexec.mainClass=org.xidian.rpcdemo.server.jdbc.NewLib
# Run the server
mvn exec:java -Dexec.mainClass=org.xidian.rpcdemo.server.MyLibGrpcServer
# In another terminal run the client
mvn exec:java -Dexec.mainClass=org.xidian.rpcdemo.client.MyLibGrpcClient
```

# to do

- [ ] GUI
- [ ] change SQL to ORM
- [ ] log