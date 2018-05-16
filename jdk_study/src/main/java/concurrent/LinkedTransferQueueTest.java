package concurrent;

import org.junit.Test;

import java.util.concurrent.LinkedTransferQueue;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/2<p/>
 * Time: 19:12<p/>
 */
public class LinkedTransferQueueTest {

    @Test
    public void testTransfer() {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();

        new Thread(() -> {
            {
                try {
                    Thread.sleep(500);  // 再改成1500
                    System.out.println("take 获取到结果:" + Thread.currentThread().getName() + "-" + queue.take());
                    System.out.println("take 获取到结果:" + Thread.currentThread().getName() + "-" + queue.take());
                    System.out.println("take 获取到结果:" + Thread.currentThread().getName() + "-" + queue.take());
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }, "consumer").start();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(Thread.currentThread().getName() + "-等待1被消耗:" + queue.tryTransfer(1));
//;                    System.out.println(Thread.currentThread().getName() + "-等待2被消耗：" + queue.tryTransfer(2, 1, TimeUnit.SECONDS));
//                try {
//                    queue.transfer(3);
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() + "-等到3被消费：true");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "prodcuer").start();
//
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void test2() throws InterruptedException {
        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                try {
//                    queue.tryTransfer(1, 3, TimeUnit.SECONDS);
                    queue.offer(2);
                    queue.offer(3);
                    queue.offer(4);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
//                    queue.transfer(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(1000);
        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
    }

}
