
package org.xidian.rpcdemo;

import org.xidian.rpcdemo.grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by rayt on 5/16/16.
 */
public class MyGrpcClient {
  public static void main(String[] args) throws InterruptedException {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
        .usePlaintext()
        .build();

    GreetingServiceGrpc.GreetingServiceBlockingStub stub =
        GreetingServiceGrpc.newBlockingStub(channel);

    HelloResponse helloResponse = stub.greeting(
        HelloRequest.newBuilder()
            .setName("Ray")
            .setAge(18)
            .setSentiment(Sentiment.HAPPY)
            .build());

    System.out.println(helloResponse);

    channel.shutdown();
  }
}