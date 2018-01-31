package simple;

import org.junit.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * User: luxiaochun<p/>
 * Date: 2017/12/20<p/>
 * Time: 16:30<p/>
 */
public class EnumTest {

    @org.junit.Test
    public void testSync() throws IOException {
        final EnumTest enumTest = new EnumTest();

        for(int i=0;i<5;i++){
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    enumTest.print1(Test.test1, finalI);
                }
            }).start();
        }

        System.in.read();
    }

    public void print1(Test test, final int i) {
        synchronized (test) {
            System.out.println(test.name() + " " + i);
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(test.name() + " " + i);
        }
    }

    public void print2(Test test) {
        synchronized (test) {
            System.out.println(test.name());
        }
    }

    enum Test{
        test1,
        test2;
    }

}
