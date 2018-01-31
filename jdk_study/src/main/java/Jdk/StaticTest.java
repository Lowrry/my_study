package Jdk;

/**
 * User: luxiaochun<p/>
 * Date: 2017/12/11<p/>
 * Time: 14:47<p/>
 */
public class StaticTest {

    public static void main(String[] args) {
        System.out.println("step 2");
        System.out.println(new User());
    }

    private static final class User{
        String name;

        static {
            System.out.println("step 1");
        }

    }

}
