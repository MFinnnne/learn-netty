package net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class MyClient {
    public static void main(String[] args) throws IOException {
        SocketChannel channel = SocketChannel.open();
        InetSocketAddress localhost = new InetSocketAddress("localhost", 3345);
        channel.connect(localhost);
        System.out.println("waiting...");
    }
}
