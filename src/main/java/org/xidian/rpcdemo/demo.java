package org.xidian.rpcdemo;

import java.io.IOException;

import org.xidian.rpcdemo.client.MyLibGrpcClient;
import org.xidian.rpcdemo.server.MyLibGrpcServer;

public class demo {
    static public void main(String[] args) throws IOException, InterruptedException{

        if(args[0].equals("-s")){
        MyLibGrpcServer.main(args);}
        else if(args[0].equals("-c"))
        {MyLibGrpcClient.main(args);}
    }
}
