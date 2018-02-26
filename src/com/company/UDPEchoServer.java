package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoServer {

    public static void main(String[] args) {

        final int LOCAL_PORT = 15900;
        final String SERVER_NAME = "localhost";

        try {
            DatagramSocket udpSocket = new DatagramSocket(LOCAL_PORT, InetAddress.getByName(SERVER_NAME));

            System.out.println("Created UDP server socket at " + udpSocket.getLocalSocketAddress() + "...");

            while (true) {

                System.out.println("Waiting for a UDP packet" + " to arrive...");

                DatagramPacket packet = new DatagramPacket(new byte[1024], 1024);

                udpSocket.receive(packet);

                displayPacketDetails(packet);

                udpSocket.send(packet);
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void displayPacketDetails(DatagramPacket packet) {

        byte[] msgBuffer = packet.getData();
        int length = packet.getLength();
        int offset = packet.getOffset();


        int remotePort = packet.getPort();

        InetAddress remoteAddr = packet.getAddress();
        String msg = new String(msgBuffer, offset, length);

        System.out.println("Received a packet:[IP Address=" + remoteAddr + ", port=" + remotePort + ", message=" + msg + "]");
    }

}
