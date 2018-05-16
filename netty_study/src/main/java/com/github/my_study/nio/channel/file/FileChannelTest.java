package com.github.my_study.nio.channel.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIO的io操作都是面向channel的
 * 传统的BIO操作都是面向流的
 *
 * User: luxiaochun<p/>
 * Date: 2018/2/24<p/>
 * Time: 16:17<p/>
 */
public class FileChannelTest {

    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("data.txt", "rw");     // 读写操作

        // 写内容
        file.writeBytes("1");
        file.writeBytes("2");
        file.writeBytes("3");
        file.writeBytes("4");

        // todo 这一步必须有,因为后续的channel.read操作也是建立在file的指针之上的.
        file.seek(0);

        FileChannel channel = file.getChannel();            // 获取文件对应的channel
        ByteBuffer buffer = ByteBuffer.allocate(10000);    // 分配一个48位的ByteBuffer, 这个不是直接内存是堆内存

        // todo 这里体现了channel的作用, 不是直接读取文件, 而是通过通道channel读取文件内容, 这样就是非阻塞的了.
        // todo channel.read底层实现,是通过native方法先将文件内容读取到直接内存中,然后复制到我们制定的堆内存buffer中.
        int bytesRead = channel.read(buffer);           // 通过channel将文件读取到buffer中
        while (bytesRead != -1) {                       // 因为一次只能读取48位,所以需要循环读取
            // todo 读取一个文件,肯定不能把所有内容都一下次读到内存中,需要将部分内容先读取到Buffer中,处理后,然后再读取剩下的内容.
            System.out.println("Read " + bytesRead);    // 打印读取的内容
            buffer.flip();                              // 切换到读指针
            while(buffer.hasRemaining()){               // 循环打印buffer内容
                System.out.print((char) buffer.get());  // get操作读指针会往后移动
            }
            buffer.clear();                             // 清空buffer,用于下一次的写
            bytesRead = channel.read(buffer);           // 继续往buffer里写
        }
        file.close();
    }

}
