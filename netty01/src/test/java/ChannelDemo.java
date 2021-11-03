import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/3 22:48
 **/
class ChannelDemo {
    @Test
    void testBuffer() {
        try (RandomAccessFile file = new RandomAccessFile("data.txt", "rw")) {

            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(10);
            do {
                int len = channel.read(buffer);
                System.out.println("读到字节数：" + len);
                if (len == -1) {
                    break;
                }
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.println("{" + (char) buffer.get() + "}");
                }
                buffer.clear();
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
