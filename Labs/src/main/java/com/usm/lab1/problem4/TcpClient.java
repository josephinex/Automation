package com.usm.lab1.problem4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TcpClient {

        private void go(){
            try{
                Socket socket = new Socket("127.0.0.1", 12900);

                InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(streamReader);

                String sonnet = null;

                while ((sonnet = bufferedReader.readLine()) != null) {
                    System.out.println("Server: " + sonnet);
                }

                bufferedReader.close();

            }catch (IOException ex){
                ex.printStackTrace();
            }
        }

        public static void main (String[] args){
            TcpClient client = new TcpClient();
            client.go();
        }
}
