
/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/6 15:06
 **/
public class HelloJni {
    static {
        System.loadLibrary("hello");
    }

    private native void sayHello();

    public static void main(String[] args) {
        new HelloJni().sayHello();
    }
}
