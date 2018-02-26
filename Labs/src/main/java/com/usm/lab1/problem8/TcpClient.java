package com.usm.lab1.problem8;

import java.io.*;
import java.net.Socket;

public class TcpClient {

    public static void main(String[] args) {

        Socket socket = null;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        BufferedReader consoleReader;
        String outMessage;

        try {
            socket = new Socket("localhost", 12950);

            System.out.println("Started client socket at " + socket.getLocalSocketAddress());

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String promptMsg = "Enter a message or bye to quit";
            System.out.println(promptMsg);

            final String[] inMsg = {null};

            Runnable receiver = () -> {
                try {
                    while ((inMsg[0] = bufferedReader.readLine()) != null) {
                        System.out.println("Server: " + inMsg[0]);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            new Thread(receiver).start();

            while ((outMessage = consoleReader.readLine()) != null) {
                if (outMessage.equalsIgnoreCase("bye")) {
                    break;
                }
                bufferedWriter.write(outMessage);
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
