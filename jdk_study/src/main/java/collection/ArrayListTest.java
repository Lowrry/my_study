package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/30<p/>
 * Time: 15:59<p/>
 */
public class ArrayListTest {

    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            list.remove(1);
        }
    }

    @Test
    public void testRemove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
//        list.add("3");

        for(String s : list) {
            if("2".equals(s)){
                list.remove(s);
            }
        }

        System.out.println(list);
    }

}
