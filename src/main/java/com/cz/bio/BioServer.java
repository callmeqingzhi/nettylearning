package com.cz.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {


    public static void main(String[] args) throws Exception {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();


        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("server start...");
        while (true) {
            // 监听，等待客户端链接
            final Socket socket = serverSocket.accept();
            System.out.println("accept a client....");
            // 连接到，就创建一个线程进行处理
            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }


    }


    public static void handler(Socket socket) {

        byte[] bytes = new byte[1024];
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {

                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
