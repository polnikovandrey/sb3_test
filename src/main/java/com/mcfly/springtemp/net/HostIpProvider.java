package com.mcfly.springtemp.net;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

public class HostIpProvider {

    private static final int TIMEOUT = 3000;

    public static void main(String... args) {
        final HostIpProvider hostIpProvider = new HostIpProvider();
        hostIpProvider.getHostAddressByName("hh.ru");
        hostIpProvider.getHostAddressByName("google.com");
        hostIpProvider.getHostAddressByName("###");
    }

    private void getHostAddressByName(String hostName) {
        try {
            final InetAddress inetAddress = Inet4Address.getByName(hostName);
            if (inetAddress.isReachable(TIMEOUT)) {
                final String hostAddress = inetAddress.getHostAddress();
                System.out.printf("hostName: [%s], hostAddress: [%s].%n", hostName, hostAddress);
            } else {
                System.out.printf("hostName: [%s] access timeout or not reachable.%n", hostName);
            }
        } catch (IOException e) {
            System.err.printf("Exception caught while trying to get hostAddress for hostname: [%s]%n[%s]%n", hostName, e);
        }
    }
}
