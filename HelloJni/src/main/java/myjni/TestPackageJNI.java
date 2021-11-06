package myjni;

/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/6 19:28
 **/
public class TestPackageJNI {
    static {
        System.loadLibrary("testpackage");
    }

    private native void sayHello();

    public static void main(String[] args) {
        new TestPackageJNI().sayHello();
    }
}
