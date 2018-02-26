package com.usm.lab1.problem9;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TcpServer {

    public static void main(String[] args) {
        TcpServer tcpServer = new TcpServer();
        tcpServer.acceptConnection();
    }

    private void acceptConnection() {
        try {
            ServerSocket serverSocket = new ServerSocket(12960, 100, InetAddress.getByName("localhost"));

            System.out.println("Server started at: " + serverSocket);

            List<ClientHandler> clientList = Collections.synchronizedList(new ArrayList<>());


            while (true) {

                System.out.println("Waiting for a connection ...");

                Socket socket = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(socket, clientList);

                clientList.add(clientHandler);

                System.out.println("Received a connection from " + socket);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static class ClientHandler implements Runnable {

        private final List<ClientHandler> clientList;
        private final Socket socket;
        private Thread self;
        InputStream inputStream;
        OutputStream outputStream;

        ClientHandler(Socket socket, List<ClientHandler> clientList) {
            this.socket = socket;
            this.clientList = clientList;
            this.self = new Thread(this);

            try {
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
                this.self.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (true) {
                    byte[] sizeAr = new byte[4];
                    inputStream.read(sizeAr);
                    int size2 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();
                    System.out.println("Received from client: " + size2);

                    byte[] imageAr = new byte[size2];
                    inputStream.read(imageAr);

                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

                    System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());

                    for (ClientHandler client : clientList) {
                        //if (client != this) {
                        System.out.println("Sending image " + image.getWidth() + image.getHeight());
                        client.sendMessage(image);
                        // }
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

                clientList.remove(this);
            }
        }

        private void sendMessage(BufferedImage image) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", byteArrayOutputStream);
            System.out.println("Image has been sent");
            byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
            outputStream.write(size);
            outputStream.write(byteArrayOutputStream.toByteArray());
            outputStream.flush();

        }
    }
}
