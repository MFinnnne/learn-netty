import util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/3 23:51
 **/
public class TestByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();
//        System.out.println(buffer.get(new byte[4]));
//        ByteBufferUtil.debugAll(buffer);
//        buffer.rewind();
//        System.out.println(buffer.get());
//        ByteBufferUtil.debugAll(buffer);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);
        buffer.mark();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);
        buffer.reset();
        ByteBufferUtil.debugAll(buffer);
        System.out.println(buffer.get());
        ByteBufferUtil.debugAll(buffer);


        buffer.rewind();
        System.out.println(buffer.get(3));

        ByteBufferUtil.debugAll(buffer);

    }
}
