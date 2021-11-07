import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Platform;


/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/7 15:57
 **/
public interface CLibrary extends Library {
    CLibrary INSTANCE =
            Native.load((Platform.isWindows() ? "msvcrt" : "c"),
                    CLibrary.class);

    /**
     * 初始化SDK 注意：调用SDK其他接口前必须先调用此接口！
     *
     * @return TRUE表示成功，FALSE表示失败
     */
    void printf(String format, Object... args);




}
