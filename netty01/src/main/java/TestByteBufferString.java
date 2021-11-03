import util.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/4 0:02
 **/
public class TestByteBufferString {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        ByteBufferUtil.debugAll(buffer);

        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        ByteBufferUtil.debugAll(hello);

        ByteBuffer wrap = ByteBuffer.wrap("hello".getBytes());
        ByteBufferUtil.debugAll(wrap);

        System.out.println(StandardCharsets.UTF_8.decode(hello));
        buffer.flip();
        System.out.println(StandardCharsets.UTF_8.decode(buffer));
    }
}
