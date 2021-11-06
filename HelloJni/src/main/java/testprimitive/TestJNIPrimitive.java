package testprimitive;


/**
 * @author MFine
 * @version 1.0
 * @date 2021/11/6 21:40
 **/
public class TestJNIPrimitive {
    static {
        System.loadLibrary("libtestprimitive_TestJNIPrimitive");
    }

    private int number = 88;

    private static int staticNumber = 21;

    private String message = "Hello from Java";

    private native double average(int n1, int n2);

    private native String transString(String str);

    private native double[] sumAndAverage(int[] numbers);

    private native void modifyInstanceVariable();

    // Declare a native method that calls back the Java methods below
    private native void nativeMethod();

    // To be called back by the native code
    private void callback() {
        System.out.println("In Java");
    }

    private void callback(String message) {
        System.out.println("In Java with " + message);
    }

    private double callbackAverage(int n1, int n2) {
        return ((double) n1 + n2) / 2.0;
    }

    // Static method to be called back
    private static String callbackStatic() {
        return "From static Java method";
    }

    private native Double[] sumAndAverageToo(Integer[] numbers);

    private native Integer getIntegerObject(int number);

    public static void main(String args[]) {
        System.out.println("transfer int :In Java, the average is " + new TestJNIPrimitive().average(3, 2));
        System.out.println("transfer string ：In Java, the transString is " + new TestJNIPrimitive().transString("hello native"));
        final double[] sumAndAverage = new TestJNIPrimitive().sumAndAverage(new int[]{1, 2, 3, 4});
        System.out.println("transfer double[] ：In Java, the sumAndAverage  sum: " + sumAndAverage[0] + " average: " + sumAndAverage[1]);

        final TestJNIPrimitive jniPrimitive = new TestJNIPrimitive();
        jniPrimitive.modifyInstanceVariable();
        System.out.println("modifyInstanceVariable:In Java, int is " + jniPrimitive.number);
        System.out.println("modifyInstanceVariable:In Java, String is " + jniPrimitive.message);
        System.out.println("modifyInstanceVariable:In Java, static number is " + staticNumber);

        final TestJNIPrimitive jniPrimitive1 = new TestJNIPrimitive();
        jniPrimitive1.nativeMethod();
        final TestJNIPrimitive jniPrimitive2 = new TestJNIPrimitive();
        System.out.println("In Java, the number is :" + jniPrimitive2.getIntegerObject(9999));

        Integer[] numbers = {11, 22, 32};
        Double[] results = new TestJNIPrimitive().sumAndAverageToo(numbers);
        System.out.println("In Java, the sum is " + results[0]);
        System.out.println("In Java, the average is " + results[1]);
    }
}
