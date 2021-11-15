package com.df.protocol;

import com.df.messgae.LoginRequestMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.logging.LoggingHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/16 0:18
 **/
class MessageCodecTest {
    @Test
    void testCodec() throws Exception {
        final EmbeddedChannel channel = new EmbeddedChannel(
                new LoggingHandler(),
                new LengthFieldBasedFrameDecoder(1024, 12, 4, 0, 0),
                new MessageCodec()
        );
        final LoginRequestMessage message = new LoginRequestMessage("张三", "123");
        final ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        new MessageCodec().encode(null, message, buffer);
        final ByteBuf s1 = buffer.slice(0, 100);
        final ByteBuf s2 = buffer.slice(100, buffer.readableBytes() - 100);
        s1.retain();
        channel.writeInbound(s1);
        channel.writeInbound(s2);
    }
}