import util.ByteBufferUtil;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/4 0:12
 **/
public class TestByteBufferScatteringReads {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("words.txt", "rw")) {
            FileChannel channel = file.getChannel();
            ByteBuffer a = ByteBuffer.allocate(3);
            ByteBuffer b = ByteBuffer.allocate(3);
            ByteBuffer c = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{a, b, c});
            a.flip();
            b.flip();
            c.flip();
            ByteBufferUtil.debugAll(a);
            ByteBufferUtil.debugAll(b);
            ByteBufferUtil.debugAll(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
