package com.usm.lab1.problem9;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

public class TcpClient {

    public static void main(String[] args) {

        Socket socket = null;
        InputStream inputStream;
        OutputStream outputStream;
        BufferedReader consoleReader;
        AtomicInteger counter = new AtomicInteger();

        try {
            socket = new Socket("localhost", 12960);

            System.out.println("Started client socket at " + socket.getLocalSocketAddress());

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String outMessage;

            Runnable receiver = () -> {
                try {
                    while (true) {
                        counter.incrementAndGet();
                        byte[] sizeAr = new byte[4];
                        inputStream.read(sizeAr);

                        int size2 = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                        byte[] imageAr = new byte[size2];
                        inputStream.read(imageAr);

                        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

                        System.out.println("Client Received Image " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());

                        ImageIO.write(image, "jpg", new File("Labs/src/main/java/com/usm/lab1/problem9/output" + counter.get() + ".jpg"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            };

            new Thread(receiver).start();
            String msg = null;

            //Client is writing the name of the image like image.jpg in console

            while ((outMessage = consoleReader.readLine()) != null) {
                if (outMessage.equalsIgnoreCase("bye")) {
                    break;
                }
                msg = outMessage;

                BufferedImage bufferedImage = ImageIO.read(new File("Labs/src/main/java/com/usm/lab1/problem9/" + msg));

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);

                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                outputStream.write(size);
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();

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
