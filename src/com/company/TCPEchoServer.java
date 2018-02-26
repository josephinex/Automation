package com.company;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServer {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12940, 100, InetAddress.getByName("localhost"));
            System.out.println("Server started at: " + serverSocket);

            while (true) {
                System.out.println("Waiting for a connection...");

                final Socket activeSocket = serverSocket.accept();

                System.out.println("Received a connection from " +
                        activeSocket);

                Runnable runnable = () -> handleClientRequest(activeSocket);

                new Thread(runnable).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void handleClientRequest(Socket socket) {

        BufferedReader socketReader = null;
        BufferedWriter socketWriter = null;

        try {
            socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            String inMsg = null;

            while ((inMsg = socketReader.readLine()) != null) {

                System.out.println("Received from client: " + inMsg);

                String outMsg = inMsg;

                socketWriter.write(outMsg);
                socketWriter.write("\n");
                socketWriter.flush();
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

