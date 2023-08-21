package com.mcfly.springtemp.net.tcp_socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private final int serverPort;

    public SocketServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void listen() {
        try (ServerSocket server = new ServerSocket(serverPort)) {
            final Socket acceptedClient = server.accept();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(acceptedClient.getInputStream()))) {
                final String input = br.readLine();
                System.out.printf("Server got request: %s.%n", input);
                final String response = input.toUpperCase();
                try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(acceptedClient.getOutputStream()))) {
                    bw.write(response);
                    bw.flush();
                    System.out.printf("Server responded: %s.%n", response);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
