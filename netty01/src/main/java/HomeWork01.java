import util.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/4 0:30
 **/
public class HomeWork01 {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,world\nI'm zhangsan\nHo".getBytes(StandardCharsets.UTF_8));
        split(source);
        source.put("w are you?\nhaha!\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        int old = source.limit();
        for (int i = 0; i < old; i++) {
            if (source.get(i) == '\n') {
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                source.limit(i+1);
                target.put(source);
                ByteBufferUtil.debugAll(target);
                source.limit(old);
            }
        }
        source.compact();
    }
}
