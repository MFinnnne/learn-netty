package net;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.ByteBufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class MyServer {
    private final static Logger log = LoggerFactory.getLogger(MyServer.class);

    public static void main(String[] args) throws IOException {

        final Selector selector = Selector.open();
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(3345));
        ssc.configureBlocking(false);
        final SelectionKey sscKey = ssc.register(selector, 0, null);
        log.debug("register key:{}", sscKey);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            //处理事件
            final Set<SelectionKey> keys = selector.selectedKeys();
            final Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                final SelectionKey key = iterator.next();
                iterator.remove();
                log.debug("key:{}", key);
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    final SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(4);
                    SelectionKey scKey = sc.register(selector, 0, buffer);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}", sc);
                } else if (key.isReadable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        final int read = channel.read(buffer);
                        if (read == -1) {
                            key.cancel();
                        } else {
                            split(buffer);
                            if (buffer.position() == buffer.limit()) {
                                final ByteBuffer byteBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                buffer.flip();
                                byteBuffer.put(buffer);
                                key.attach(byteBuffer);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                }
            }

        }
    }


    private static void split(ByteBuffer source) {
        source.flip();
        int old = source.limit();
        for (int i = 0; i < old; i++) {
            if (source.get(i) == '\n') {
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                source.limit(i + 1);
                target.put(source);
                ByteBufferUtil.debugAll(target);
                source.limit(old);
            }
        }
        source.compact();
    }

}
