package test;

import java.util.HashMap;
import java.util.Map;

/**
 * User: luxiaochun<p/>
 * Date: 2017/9/1<p/>
 * Time: 15:00<p/>
 */
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Map<String,String> map = new HashMap<>();
        test1.val(map);

        System.out.println(map);
    }

    public void val(Map<String, String> map) {
        map.put("1", "2");
    }

}
