import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import org.junit.jupiter.api.Test;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/7 15:54
 **/
public class TestJNA {

    @Test
    void testVixHz_InitSDK() {
        CLibrary.INSTANCE.printf("fuck");
    }


    @Test
    void testJNA2() {
        Kernel32 INSTANCE = (Kernel32)
                Native.load("kernel32", Kernel32.class);
// Optional: wraps every call to the native library in a
// synchronized block, limiting native calls to one at a time
        Kernel32 SYNC_INSTANCE = (Kernel32)
                Native.synchronizedLibrary(INSTANCE);
        Kernel32.SYSTEMTIME time = new Kernel32.SYSTEMTIME();
        INSTANCE.GetSystemTime(time);

        System.out.println("Today's integer value is " + time.wDay);
    }


    @Test
    void testJNA3() {
        MYTestJNA.INSTANCE.sayHello();
        System.out.println("return from c++: " + MYTestJNA.INSTANCE.testReturnInt());
        int[] a = {1, 2, 3, 4};
        double[] d = new double[2];
         MYTestJNA.INSTANCE.returnArray(d, a, "lucky", 4);
        for (double lucky : d) {
            System.out.println(lucky);
        }
    }
}
