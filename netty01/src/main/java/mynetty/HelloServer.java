package mynetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import net.MyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HelloServer {
    private final static Logger log = LoggerFactory.getLogger(MyServer.class);
    public static void main(String[] args) {
        DefaultEventLoopGroup normalworker = new DefaultEventLoopGroup(2);
        new ServerBootstrap()
                .group(new NioEventLoopGroup(2)) // 1
                .channel(NioServerSocketChannel.class) // 2
                .childHandler(new ChannelInitializer<NioSocketChannel>() { // 3
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                        ch.pipeline().addLast(normalworker,"myhandler",new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = msg instanceof ByteBuf ? ((ByteBuf) msg) : null;
                                if (byteBuf != null) {
                                    byte[] buf = new byte[16];
                                    ByteBuf len = byteBuf.readBytes(buf, 0, byteBuf.readableBytes());
                                    log.debug(new String(buf));
                                }
                            }
                        }); // 5
                    }
                })
                .bind(8080); // 4
    }
}
