package com.broker.client;

public class ProduceClient {
    public static void main(String[] args) throws Exception {
        MqClient.produce(Math.random() + "");
    }
}
