package com.github.my_study;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 16:56<p/>
 */
public class FuturesTest {

    @Test
    public void test1() throws IOException {
//        ListenableFuture
        ExecutorService executorService = Executors.newCachedThreadPool();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);

        ListenableFuture<?> future = listeningExecutorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("test start ---");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("test end ---");
            }
        });

        future.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am notifyed 1---");
            }
        }, MoreExecutors.directExecutor());

        future.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println("i am notifyed 2---");
            }
        }, MoreExecutors.directExecutor());

        System.in.read();
    }

}
