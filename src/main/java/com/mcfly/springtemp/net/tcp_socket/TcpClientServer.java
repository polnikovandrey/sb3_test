package com.mcfly.springtemp.net.tcp_socket;

public class TcpClientServer {

    private static final int SERVER_PORT = 9999;

    public static void main(String... args) {
        new Thread(() -> new SocketServer(SERVER_PORT).listen()).start();
        new SocketClient(SERVER_PORT).sendRequest();
    }
}
