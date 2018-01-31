package collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/30<p/>
 * Time: 13:46<p/>
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        final LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>(Arrays.asList("1", "2", "3"));

        Iterator<String> it = queue.iterator();
        final LockByTurn lockByTurn = new LockByTurn();

        new Thread(new Runnable() {
            @Override
            public void run() {
                lockByTurn.acquireFirst();
                try {
                    String s1 = queue.poll();
                    System.out.println("poll : " + s1);
                    System.out.println(queue);
                } finally {
                    lockByTurn.releaseSecond();
                }

                //==========================================

                /**
                 * 避免两个同时执行,如果只是简单的一个锁,肯定不行的,因为两个线程都可以竞争
                 * 需要用闭锁?,让两个线程只有一个可以通过,
                 * 但是怎么才能实现交替执行?
                 * a线程先执行,然后释放b线程的闭锁,然后打开a线程的闭锁; b线程执行,然后释放a线程的闭锁,...这种是可以的
                 */
                lockByTurn.acquireFirst();
                try{
                    String s2 = queue.poll();
                    System.out.println("poll : " + s2);
                    System.out.println(queue);
                } finally {
                    lockByTurn.releaseSecond();
                }


                lockByTurn.acquireFirst();
                try{
                    queue.offer("4");
                    System.out.println("put : " + 4);
                    System.out.println(queue);
                } finally {
                    lockByTurn.releaseSecond();
                }


                lockByTurn.acquireFirst();
                try{
                    queue.offer("5");
                    System.out.println("put : " + 5);
                    System.out.println(queue);
                } finally {
                    lockByTurn.releaseSecond();
                }


                lockByTurn.acquireFirst();
                try{
                    String s3 = queue.poll();
                    System.out.println("poll : " + s3);
                    System.out.println(queue);
                } finally {
                    lockByTurn.releaseSecond();
                }

            }
        }).start();


        while (it.hasNext()) {
            lockByTurn.acquireSecond();
            try{
                String next = it.next();
                System.out.println("[Print] next = " + next);
                System.out.println(queue);
            }finally {
                lockByTurn.releaseFirst();
            }
        }


    }

    //两个线程交替执行
    static class LockByTurn{

        Semaphore firstSp = new Semaphore(1);
        Semaphore secondSp = new Semaphore(0);

        public void acquireFirst() {
            firstSp.acquireUninterruptibly();
        }

        public void releaseFirst(){
            firstSp.release();
            System.out.println("====================");
        }

        public void acquireSecond() {
            secondSp.acquireUninterruptibly();
        }

        public void releaseSecond(){
            secondSp.release();
            System.out.println("====================");
        }

    }

}
