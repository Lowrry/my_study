package bigdecimal;

import java.io.IOException;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/7<p/>
 * Time: 9:47<p/>
 */
public class BigDecimalTest {

    public static void main(String[] args) throws IOException {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        User user = new User();
                        System.out.println(user);
                    }
                }
        ).start();

        System.out.println("I am here !");
        System.in.read();
    }

}

class User{

    String name = "test";

    @Override
    protected void finalize() throws Throwable {
        System.out.println("i am gc !");
        super.finalize();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
