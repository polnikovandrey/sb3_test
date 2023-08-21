package com.mcfly.springtemp.net.cilent_server;

import java.io.*;
import java.net.Socket;

public class SocketClient {

    private final int serverPort;

    public SocketClient(int serverPort) {
        this.serverPort = serverPort;
    }

    public void sendRequest() {
        try (Socket client = new Socket("localhost", serverPort);
             BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter some lower case input:");
            final String consoleInput = consoleReader.readLine();
            try (BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
                socketWriter.write(consoleInput);
                socketWriter.newLine();
                socketWriter.flush();
                try (BufferedReader socketReader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    final String response = socketReader.readLine();
                    System.out.printf("Client got response: %s.%n", response);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
