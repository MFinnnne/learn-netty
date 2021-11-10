package net;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.ILoggerFactory;
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
    final static Logger log = LoggerFactory.getILoggerFactory().getLogger(MyServer.class.getName());

    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(3345));
        ArrayList<SocketChannel> channels = new ArrayList<>();
        while (true) {
            log.debug("connecting...");
            SocketChannel sc = socketChannel.accept();
            if (sc == null) {
                continue;
            }
            sc.configureBlocking(false);
            log.debug("connected....{}", sc);
            channels.add(sc);
            for (SocketChannel channel : channels) {
                log.debug("before read ...{}", channel);
                channel.read(byteBuffer);
                byteBuffer.flip();
                ByteBufferUtil.debugRead(byteBuffer);
                byteBuffer.clear();
                log.debug("after read ...{}", channel);
            }
        }
    }

}
