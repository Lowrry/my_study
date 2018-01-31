/**
 * User: luxiaochun<p/>
 * Date: 2017/6/13<p/>
 * Time: 15:41<p/>
 */
public class RecursionTest {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(3000);

        new RecursionTest().print(1);

    }

    void print(int num) throws InterruptedException {
        User user = new User();
//        try {
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println(num);

        if (num == 10000) {
            return;
        } else {
            print(num++);
        }
    }

    class User {
//        Long id = atomicLong.incrementAndGet();
    }

}