package com.broker.client;

public class ConsumeClient {
    public static void main(String[] args) throws Exception {
        System.out.println("获取的消息为: " + MqClient.consume());
    }
}
