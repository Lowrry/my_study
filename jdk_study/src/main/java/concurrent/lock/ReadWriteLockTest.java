package concurrent.lock;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/17<p/>
 * Time: 17:55<p/>
 */
public class ReadWriteLockTest {
    final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public void read() {
        System.out.println("Thread1 lock ...");
        writeLock.lock();
        System.out.println("Thread1 doing ...");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        writeLock.unlock();
        System.out.println("Thread1 unlock ...");
    }


    public void write() {
        System.out.println("Thread2 lock ...");
        readLock.lock();
        System.out.println("Thread2 doing ...");
        readLock.unlock();
        System.out.println("Thread2 unlock ...");
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        final ReadWriteLockTest test = new ReadWriteLockTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.read();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.write();
            }
        }).start();

        System.in.read();
    }

}
