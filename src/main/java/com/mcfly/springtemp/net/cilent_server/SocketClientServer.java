package com.mcfly.springtemp.net.cilent_server;

public class SocketClientServer {

    private static final int SERVER_PORT = 9999;

    public static void main(String... args) {
        new Thread(() -> new SocketServer(SERVER_PORT).listen()).start();
        new SocketClient(SERVER_PORT).sendRequest();
    }
}
