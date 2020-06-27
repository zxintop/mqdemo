package com.broker.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BrokerServer implements Runnable {
    public static int SERVICE_PORT = 9999;

    private final Socket socket;

    public BrokerServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream());) {

            String str = in.readLine();
            if (str == null) {
                return;
            }
            System.out.println("接收到原始数据: " + str);

            if (str.equals("CONSUME")) {
                String message = Broker.consume();
                out.println(message);
                out.flush();
            } else {
                Broker.produce(str);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        ServerSocket server = new ServerSocket(SERVICE_PORT);
        while (true) {
            BrokerServer broker = new BrokerServer(server.accept());
            pool.submit(broker);
        }
    }
}
