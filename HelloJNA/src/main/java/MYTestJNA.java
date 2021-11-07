import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/7 19:47
 **/
public interface MYTestJNA extends Library {

    MYTestJNA INSTANCE =
            Native.load("libtest-jna",
                    MYTestJNA.class);

    void sayHello();


    int testReturnInt();

    void returnArray(double[] d, int[] a, String b, int length);

    void sendString(String val);

    void getString(PointerByReference val);

    void cleanup(Pointer p);


}

