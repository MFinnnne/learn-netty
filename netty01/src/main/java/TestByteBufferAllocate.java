import java.nio.ByteBuffer;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/3 23:43
 **/
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(   16).getClass());
    }
}
