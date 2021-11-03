import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/4 0:19
 **/
public class TestGatheringWriters {
    public static void main(String[] args) {
        ByteBuffer hello = StandardCharsets.UTF_8.encode("hello");
        ByteBuffer hello1 = StandardCharsets.UTF_8.encode("hello1");
        ByteBuffer hello2 = StandardCharsets.UTF_8.encode("hello2");

        try (FileChannel rw = new RandomAccessFile("words2.txt", "rw").getChannel()) {
            rw.write(new ByteBuffer[]{hello,hello1,hello2});
        } catch (IOException e) {
        }
    }
}
