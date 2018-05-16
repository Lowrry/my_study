package generic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/6<p/>
 * Time: 17:26<p/>
 */
public class GenericTest {

    // 测试泛型强转报错
    @Test
    public void test(){
        List list = new ArrayList();
        list.add("1");
        list.add(new String[]{"1","2"});

        System.out.println((List<String>)list);

    }





}
