package collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/23<p/>
 * Time: 9:23<p/>
 */
public class HashMapTest {

    @Test
    public void test1() {
        List users = Arrays.asList(new User(), new User());

        List<User> users1 = (List<User>) users;
        System.out.println(users1);
    }

    @Test
    public void test2() {   // 测试hash桶数据分布是否均匀
        HashMap map = new HashMap();

        for(int i=0;i<32;i++){
            map.put(i, i);
        }

        System.out.println(map);

    }

}

class User{
    String name = "Test";

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
