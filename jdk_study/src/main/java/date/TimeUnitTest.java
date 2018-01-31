package date;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/3<p/>
 * Time: 14:44<p/>
 */
public class TimeUnitTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        try {
            lock.lock();
            System.out.println("1 ... lock");
            lock.lock();
            System.out.println("2 ... lock");

        } finally {
            lock.unlock();
        }
        System.out.println("come to here ...");
        System.out.println("is locked : " + lock.isLocked());
        System.out.println("lock holed count : " + lock.getHoldCount());
    }

}
