package com.usm.lab1.problem2;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
            ServerSocket serverSocket = new ServerSocket(12900, 100, InetAddress.getByName("localhost"));

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
        BufferedReader socketReader;
        BufferedWriter socketWriter;
        ClientHandler client;
        int rand;

        ClientHandler(Socket socket, List<ClientHandler> clientList) {
            this.socket = socket;
            this.clientList = clientList;
            this.self = new Thread(this);

            try {
                this.socketReader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                this.socketWriter = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
                this.self.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                String message = null;

                while ((message = socketReader.readLine()) != null) {

                    System.out.println("Received from client: " + message);

                    while(true) {
                        rand = (int) (Math.random() * clientList.size());

                        System.out.println("I am number " + clientList.indexOf(this));
                        System.out.println("I am " + clientList.get(clientList.indexOf(this)));
                        System.out.println("Client " + clientList.get(rand));
                        System.out.println("Random number : " + rand);

                        for(ClientHandler client : clientList){
                            System.out.println(client.toString());
                        }

                        if ((client = clientList.get(rand)) != this) {
                            client.sendMessage(message);
                            System.out.println("Message has been sent");
                            break;
                        }
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

        private void sendMessage(String message) throws IOException {
            socketWriter.write(message + "\r\n");
            System.out.println("Message has been sent" + message);
            socketWriter.flush();
        }
    }
}
