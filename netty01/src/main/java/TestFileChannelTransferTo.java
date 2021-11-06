import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/6 0:20
 **/
public class TestFileChannelTransferTo {

    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("to.txt").getChannel();
        ) {
            long size = from.size();
            for (long left = size; left > 0; ) {
                System.out.println((size - left) + "--->" + left);
                left -= from.transferTo((size - left), left, to);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
