package bit;

import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/5<p/>
 * Time: 14:54<p/>
 */
public class BitTest {

    byte[] data = new byte[1000000];

    @Test
    public void test1() {
//        int i=0;
//        BitTest bitTest = new BitTest();
//        bitTest.writeInt(i, 1231131231);
//
//        System.out.println(bitTest.data);

        System.out.println((byte) 1231131231);
        System.out.println(Integer.toBinaryString(1231131231));
        System.out.println(Integer.parseInt("01011111", 2));
    }

    private int writeInt(int i, int value) {    //占byte[]4个单位
        data[i++] = (byte) (value >>> 24);
        data[i++] = (byte) (value >>> 16);
        data[i++] = (byte) (value >>> 8);
        data[i++] = (byte) value;
        return i;
    }

    @Test
    public void testSpeed() {
        final long LENGTH = 1000000000l;

        // 测试位操作和正常运算的速度差距
        int a = 7;

        long start = System.currentTimeMillis();
        for (long i = 0L; i < LENGTH; i++) {
            long b = 7 & i;
        }
        System.out.println("use time = " + (System.currentTimeMillis() - start));


        long start1 = System.currentTimeMillis();
        for (long i = 0L; i < LENGTH; i++) {
            long b = i % 8;
        }
        System.out.println("use time = " + (System.currentTimeMillis() - start1));
    }

}
