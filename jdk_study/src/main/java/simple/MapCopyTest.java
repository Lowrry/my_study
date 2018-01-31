package simple;

import java.util.HashMap;
import java.util.Map;

/**
 * User: luxiaochun<p/>
 * Date: 2017/8/16<p/>
 * Time: 20:35<p/>
 */
public class MapCopyTest {

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("1","2");
        Map<String,String> map1 = new HashMap<>(map);
        map.clear();
        System.out.println("map = " + map);
        System.out.println("map1 = " + map1);
    }

}
