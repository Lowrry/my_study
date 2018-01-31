package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/30<p/>
 * Time: 16:29<p/>
 */
public class LinkedListTest {

    public void testIterator() {
        List<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();

        iterator.next();
    }

}
