package collection;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/2<p/>
 * Time: 11:36<p/>
 */
public class SynchronousQueueTest {

    @Test
    public void testOffer() throws InterruptedException {
        final SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String result = synchronousQueue.take();
                    System.out.println("take result = " + result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println(synchronousQueue.offer("1"));
        System.out.println(synchronousQueue.offer("2"));

        String result = synchronousQueue.take();

        System.out.println(synchronousQueue);
    }

    @Test
    public void testPut() throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        synchronousQueue.put("1");

        System.out.println(synchronousQueue);
    }

}
