package org.xidian.rpcdemo.client;

import org.xidian.rpcdemo.client.controller.Operator;
import org.xidian.rpcdemo.client.ui.sampleMemu;

public class MyLibGrpcClient {
  public static void main(String[] args) throws InterruptedException {

    Operator.init(args);
    sampleMemu.memu();
    Operator.shutdown();

    return;
  }
}