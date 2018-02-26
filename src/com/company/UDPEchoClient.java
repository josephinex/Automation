package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPEchoClient {

    public static void main(String[] args) {

        DatagramSocket udpSocket = null;
        BufferedReader br = null;

        try {
            udpSocket = new DatagramSocket();
            String msg = null;
            br = new BufferedReader(new InputStreamReader(System.in));
            String promptMsg = "Please enter a message (Bye to quit):";

            System.out.print(promptMsg);

            while ((msg = br.readLine()) != null) {

                if (msg.equalsIgnoreCase("bye")) {
                    break;
                }

                DatagramPacket packet = UDPEchoClient.getPacket(msg);

                udpSocket.send(packet);
                udpSocket.receive(packet);

                displayPacketDetails(packet);

                System.out.print(promptMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (udpSocket != null) {
                udpSocket.close();
            }
        }
    }

    public static void displayPacketDetails(DatagramPacket packet) {


        byte[] msgBuffer = packet.getData();
        int length = packet.getLength();
        int offset = packet.getOffset();
        int remotePort = packet.getPort();

        InetAddress remoteAddr = packet.getAddress();

        String msg = new String(msgBuffer, offset, length);

        System.out.println("[Server at IP Address=" + remoteAddr +
                ", port=" + remotePort + "]: " + msg);

        System.out.println();
    }

    public static DatagramPacket getPacket(String msg) throws UnknownHostException {

        final int PACKET_MAX_LENGTH = 1024;
        byte[] msgBuffer = msg.getBytes();

        int length = msgBuffer.length;

        if (length > PACKET_MAX_LENGTH) {
            length = PACKET_MAX_LENGTH;
        }

        DatagramPacket packet = new DatagramPacket(msgBuffer, length);

        int serverPort = 15900;
        final String SERVER__NAME = "localhost";
        InetAddress serverIPAddress = InetAddress.getByName(SERVER__NAME);

        packet.setAddress(serverIPAddress);
        packet.setPort(serverPort);

        return packet;
    }
}

