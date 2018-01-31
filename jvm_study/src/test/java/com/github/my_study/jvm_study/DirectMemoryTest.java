package com.github.my_study.jvm_study;

import org.junit.Test;
import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/17<p/>
 * Time: 15:43<p/>
 */
public class DirectMemoryTest {

    /**
     * 测试直接内存和堆内存 分配速度
     * <p>
     * 直接内存的OOM和堆内存的OOM有点不同,
     * 直接内存使用的是非堆内存,也就是堆内存以外的系统内存
     * 比如NIO就使用了直接内存,如果向系统申请直接内存失败,就会报错OOM
     */
    @Test
    public void testSpeed() {
        int NUM = 1000000;
        int capacity = 1000;

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < NUM; i++) {
            ByteBuffer.allocate(capacity);
        }
        System.out.println("use time = " + (System.currentTimeMillis() - start1));

        long start2 = System.currentTimeMillis();
        for (int i = 0; i < NUM; i++) {
            ByteBuffer.allocateDirect(capacity);
        }
        System.out.println("use time = " + (System.currentTimeMillis() - start2));
    }

    // 测试直接内存和对内存的读写速度
    @Test
    public void testWriteAndRead() {
        int NUM = 1000000;
        int capacity = 1000;

        ByteBuffer buffer1 = ByteBuffer.allocateDirect(capacity);   //分配DirectBuffer
        long start1 = System.currentTimeMillis();
        writeAndRead(NUM, buffer1);
        System.out.println("DirectBuffer use : " + (System.currentTimeMillis() - start1) + "ms");


        ByteBuffer buffer2 = ByteBuffer.allocate(capacity);     //分配ByteBuffer
        long start2 = System.currentTimeMillis();
        writeAndRead(NUM, buffer2);
        System.out.println("ByteBuffer use : " + (System.currentTimeMillis() - start2) + "ms");
    }

    private void writeAndRead(int NUM, ByteBuffer buffer) {
        for (int i = 0; i < NUM; i++) {
            for (int j = 0; j < 99; j++) {
                buffer.putInt(j);           //写入数据
            }
            buffer.flip();                  //读指针从缓存的头开始读
            for (int j = 0; j < 99; j++) {
                buffer.get();               //读取数据
            }
            buffer.clear();                 //清空buffer
        }
    }

    // 直接内存回收的问题

    //-server -Xms20m -Xmx64m
    //-XX:MaxDirectMemorySize = 10m
    @Test
    public void testReserve() {
//        DirectByteBuffer 直接内存.
//        Bits.reserveMemory    分配内存,报错的地方.


        long max = VM.maxDirectMemory();
        System.out.println("Default max DirectMemory = " + (max / 1024) / 1024 + " M");
    }

}
