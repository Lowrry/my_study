package concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/27<p/>
 * Time: 11:32<p/>
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        lock.lock();
        try {
            System.out.println(lock.isHeldByCurrentThread());
            System.out.println(lock.isFair());
            System.out.println(lock.isLocked());
        }finally {
            lock.unlock();
            System.out.println(lock.isHeldByCurrentThread());
            System.out.println(lock.isFair());
            System.out.println(lock.isLocked());
        }

    }

}
