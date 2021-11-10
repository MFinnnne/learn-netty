import util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/3 22:56
 **/
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[] {0x62, 0x63, 0x64});
        ByteBufferUtil.debugAll(buffer);
        buffer.flip();
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);
        buffer.compact();
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[] {0x65, 0x66});
        ByteBufferUtil.debugAll(buffer);
    }
}
