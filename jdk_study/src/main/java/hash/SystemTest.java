package hash;

import org.junit.Test;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/13<p/>
 * Time: 17:34<p/>
 */
public class SystemTest {

    @Test
    public void testHashCode(){
        System.out.println(System.identityHashCode(new String[]{"1","2","3"}));
        System.out.println(System.identityHashCode(new String[]{"1","2","3"}));
        System.out.println(System.identityHashCode(new String[]{"1","2","3"}));
    }

}
