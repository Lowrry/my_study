package algorithm;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/16<p/>
 * Time: 11:05<p/>
 */
public class Binary {

    int bit;    //多少进制的
    int value;  //值

    public Binary(int bit, int value) {
        this.bit = bit;
        this.value = value;
    }

    public void add(int add) {
        value += add;
    }

    public String get() {
        return Integer.toString(value, bit);
    }

    public static int getValue(int bit, String code) {
        return Integer.valueOf(code, bit);
    }

}
