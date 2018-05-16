package statics;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/28<p/>
 * Time: 16:22<p/>
 */
public class ConstructorTest {

    String s;

    public ConstructorTest(String s) {
        this.s = 1/0 + "";
    }

    public static void main(String[] args) {
        System.out.println(new ConstructorTest("1"));
        System.out.println(new ConstructorTest("2"));
    }

}
