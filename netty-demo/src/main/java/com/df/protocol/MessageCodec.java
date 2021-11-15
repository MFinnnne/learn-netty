package com.df.protocol;

import com.df.messgae.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/15 23:29
 **/
@Slf4j
public class MessageCodec extends ByteToMessageCodec<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        // 4字节 魔数
        out.writeBytes(new byte[]{1, 2, 3, 4});
        // 1字节  协议版本
        out.writeByte(1);
        // 1字节 序列化方式  0 jdk  1 json
        out.writeByte(0);
        // 1字节 指令类型
        out.writeByte(msg.getMessageType());
        // 4字节 包顺序
        out.writeInt(msg.getSequenceId());
        // 对齐填充
        out.writeByte(0xff);

        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        final byte[] bytes = bos.toByteArray();
        //长度
        out.writeInt(bytes.length);
        // 写入内容
        out.writeBytes(bytes);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int magicNum = in.readInt();
        final byte version = in.readByte();
        final byte serializerType = in.readByte();
        final byte messageType = in.readByte();
        final int sequenceId = in.readInt();
        in.readByte();
        final int length = in.readInt();
        final byte[] bytes = new byte[length];
        in.readBytes(bytes,0,length);
        final ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        final Message message = (Message)ois.readObject();
        log.debug("{}, {}, {}, {}, {}, {}", magicNum, version, serializerType, messageType, sequenceId, length);
        log.debug("{}", message);
        out.add(message);
    }
}
