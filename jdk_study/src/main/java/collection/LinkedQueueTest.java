package collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/30<p/>
 * Time: 15:24<p/>
 */
public class LinkedQueueTest {

    @Test
    public void test(){
        LinkedBlockingQueue<String> s = new LinkedBlockingQueue<>();
        Iterator<String> iterator = s.iterator();
        System.out.println(iterator.next());
    }

}
