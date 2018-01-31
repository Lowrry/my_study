package bit;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/18<p/>
 * Time: 14:59<p/>
 */
public class YihuoTest {

    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("100000000000000000000000000000", 2));
//
        int newCapacity = 536870912;
        print(newCapacity);
        newCapacity |= newCapacity >>>  1;  // |= 异或计算
        print(newCapacity);
        newCapacity |= newCapacity >>>  2;
        print(newCapacity);
        newCapacity |= newCapacity >>>  4;
        print(newCapacity);
        newCapacity |= newCapacity >>>  8;
        print(newCapacity);
        newCapacity |= newCapacity >>> 16;
        print(newCapacity);
        newCapacity ++;
        print(newCapacity);

        System.out.println(newCapacity);
    }

    private static void print(int a){
        System.out.println("==> " + Integer.toBinaryString(a));
    }

}
