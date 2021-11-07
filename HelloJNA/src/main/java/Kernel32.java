import com.sun.jna.Structure;
import com.sun.jna.win32.StdCallLibrary;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/7 17:05
 **/
public interface Kernel32 extends StdCallLibrary {

    @Structure.FieldOrder({ "wYear", "wMonth", "wDayOfWeek", "wDay", "wHour", "wMinute", "wSecond", "wMilliseconds" })
    public static class SYSTEMTIME extends Structure {
        public short wYear;
        public short wMonth;
        public short wDayOfWeek;
        public short wDay;
        public short wHour;
        public short wMinute;
        public short wSecond;
        public short wMilliseconds;
    }

    void GetSystemTime(SYSTEMTIME result);
}
