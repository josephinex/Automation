package com.usm.lab1.problem4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpServer {

    List<String> list = new ArrayList<>();

    private List<String> parseSonets() {

        File file = new File("Labs/src/main/java/com/usm/lab1/problem4/sonet.txt");
        BufferedReader reader = null;
        String msg = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(file));

            while ((msg = reader.readLine()) != null) {

                stringBuilder.append(msg + "\n");

                if(msg.isEmpty()){
                    list.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            }

            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private void go() {

        ServerSocket serverSock = null;

        try {
            serverSock  = new ServerSocket(12900);

            while (true) {

                System.out.println("Waiting for a connection ...");

                Socket socket = serverSock.accept();

                System.out.println("Received a connection from " + socket);

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                writer.write(getSonet() + "\n");
                writer.flush();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            try {
                serverSock.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getSonet() {

        List<String> sonets = parseSonets();

        int random = (int) (Math.random() * list.size());

        return  sonets.get(random);
    }

    public static void main(String[] args) {
        TcpServer server = new TcpServer();
        server.go();
    }
}
