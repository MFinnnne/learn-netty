package c01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringEncoder;

public class DecoderClient {
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new LengthFieldPrepender(2,0,false));
                    ch.pipeline().addLast(new StringEncoder());
                    ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        // 在于server建立连接后，即发送请求报文
                        public void channelActive(ChannelHandlerContext ctx) {
                            ctx.writeAndFlush("i am request!");
                            ctx.writeAndFlush("i am a anther request!");
                        }
                    });
                }
            });
            // Start the client.
            ChannelFuture f = b.connect("127.0.0.1", 8080).sync(); // (5)
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
