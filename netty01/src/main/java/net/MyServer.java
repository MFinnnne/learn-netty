package net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class MyServer {
    private final static Logger log = LoggerFactory.getLogger(MyServer.class);
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(3345));
        ArrayList<SocketChannel> channels = new ArrayList<>();
        socketChannel.configureBlocking(false);
        log.debug("connecting...");
        while (true) {
            SocketChannel sc = socketChannel.accept();
            if (sc != null) {
                sc.configureBlocking(false);
                log.debug("connected....{}", sc);
                channels.add(sc);
            }
            for (SocketChannel channel : channels) {
                int read = channel.read(byteBuffer);
                if (read > 0) {
                    log.debug("before read ...{}", channel);
                    byteBuffer.flip();
                    ByteBufferUtil.debugRead(byteBuffer);
                    byteBuffer.clear();
                    log.debug("after read ...{}", channel);
                }
            }
        }
    }

}
