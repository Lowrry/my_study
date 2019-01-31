package com.github.my_study.cache;

import com.google.common.cache.*;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.ListenableFuture;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2018/11/8<p/>
 * Time: 16:43<p/>
 */
public class GuavaCache {

    @Test
    public void testRefresh() throws IOException, InterruptedException {
        LoadingCache<String, String> loader = CacheBuilder.newBuilder()
                .recordStats()
                .refreshAfterWrite(3, TimeUnit.SECONDS)
                .concurrencyLevel(16)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println("I am removed " + notification.getKey() + " , " + notification.getValue());
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        Thread.sleep(500);
                        System.out.println("Real load data : " + key);
                        return "simple_" + key;
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        Thread.sleep(500);
                        System.out.println("I am refreshed ...");
                        return super.reload(key, oldValue);
                    }
                });

        System.out.println(loader.getUnchecked("111"));
        System.out.println(loader.getUnchecked("222"));

        System.out.println("===========");

//        Thread.sleep(1000);
        System.out.println(loader.getUnchecked("222"));

        Thread.sleep(3000);
        System.out.println(loader.getUnchecked("222"));
        System.out.println(loader.getUnchecked("222"));


        System.in.read();

        /*
        1. 如果正在加载, 其他请求会被阻塞么? 断点是怎么走的, 看一下.
        2. 如果正在refresh会怎么样
         */
    }


    @Test
    public void testLoading() throws IOException {
        LoadingCache<String, String> loader = CacheBuilder.newBuilder()
                .recordStats()
                .refreshAfterWrite(1, TimeUnit.MILLISECONDS)
                .concurrencyLevel(16)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("Real load data : " + key + ", current time = " + System.currentTimeMillis());
                        return "simple_" + key + System.currentTimeMillis();
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        Thread.sleep(1000 * 3);
                        System.out.println("I am refreshed ...");
                        return super.reload(key, oldValue);
                    }
                });

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " started.");
                    String result = loader.getUnchecked("lxc_test");
                    System.out.println("result = " + result + ", current time = " + System.currentTimeMillis());
                }
            }, "Thread_"+i).start();
            // 当其中一个线程加载数据, 其他也会造成数据刷新的操作不会再触发数据刷新, 会直接返回旧的值.
        }

        System.in.read();
    }

}
