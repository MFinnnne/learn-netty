package net;



import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class MyClient1 {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 3345));
        System.out.println("waiting...");
    }
}
