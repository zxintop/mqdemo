package com.broker.server;

import java.util.concurrent.ArrayBlockingQueue;

public class Broker {
    private final static int MAX_SIZE = 3;

    private static ArrayBlockingQueue<String> messageQueue = new ArrayBlockingQueue<>(MAX_SIZE);

    public static void produce(String msg) {
        if (messageQueue.offer(msg)) {
            System.out.println("成功发送消息: " + msg + ",当前消息中心暂存消息数为: " + messageQueue.size());
        } else {
            System.out.println("消息中心暂存消息达到最大数,无法继续放入消息");
        }
        System.out.println("========================================");
    }

    public static String consume() {
        String msg = messageQueue.poll();

        if (msg != null) {
            System.out.println("消费消息: " + msg + ", 当前暂存消息数为: " + messageQueue.size());
        } else {
            System.out.println("消息中心内没有消息可以消费");
        }
        System.out.println("========================================");

        return msg;
    }
}
