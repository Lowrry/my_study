package com.github.my_study.jvm_study;

import io.netty.util.Recycler;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Netty对象池的例子
 *
 * User: luxiaochun<p/>
 * Date: 2018/1/16<p/>
 * Time: 15:20<p/>
 */
public class RecyclerTest {

    static class WrapRecycler {

        private List<String> list;

        private final static Recycler<WrapRecycler> RECYCLER = new Recycler<WrapRecycler>() {
            // 注意到这里Recycler变量是static变量,永远不会被回收的,内部自己维护对象的生命周期
            // 并且这个是一个线程本地变量, 所以每个对象池都是线程私有的.
            @Override
            protected WrapRecycler newObject(Handle<WrapRecycler> handle) {
                return new WrapRecycler(handle);
            }
        };

        Recycler.Handle<WrapRecycler> handle;

        WrapRecycler(Recycler.Handle<WrapRecycler> handle) {
            this.handle = handle;
            this.list = new ArrayList<>(1000);
        }

        List<String> getList() {
            return list;
        }

        static WrapRecycler getInstance() {
            return RECYCLER.get();
        }

        void recycle() {
            handle.recycle(this);
        }
    }

    @Test
    public void testDifferentThreadRecycle() throws InterruptedException, IOException {
        System.out.println("Main thread started ...");
        final WrapRecycler instance = WrapRecycler.getInstance();
        instance.getList().add("111");      // main 线程放入一个字符串
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        new Thread(new Runnable() {         // 这里新创建一个线程,在新的线程中可以回收main线程中的对象.
            @Override
            public void run() {
                System.out.println("Sub thread started ...");

                List<String> list = instance.getList();
                list.add("222");            // 子线程放入一个字符串. 当然这里可以回收任何对象. 最好回收代价很高的对象.

                instance.recycle();         // 对main线程从对象池中回去的对象家进行回收动作.

                System.out.println("Sub Thread get list : " + WrapRecycler.getInstance().getList());    // 在子线程中从对象池获取对象
                countDownLatch.countDown();
            }
        }).start();

        countDownLatch.await();

        System.out.println("Main Thread get list : " + WrapRecycler.getInstance().getList());           // 在主线程中从对象池获取对象
        System.in.read();
    }
}
