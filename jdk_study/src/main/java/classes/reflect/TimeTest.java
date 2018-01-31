package classes.reflect;

import com.sun.istack.internal.NotNull;

/**
 * User: luxiaochun<p/>
 * Date: 2017/6/29<p/>
 * Time: 11:15<p/>
 */
public class TimeTest {

    public static void main(String[] args) throws NoSuchMethodException {
        long start = System.currentTimeMillis();
//        Method method = TimeTest.class.getMethod("cal", String.class, String.class, String.class);
//        method.getParameterAnnotations();
//


        StringBuilder stringBuilder = new StringBuilder("PREFIX_");
        System.out.println(stringBuilder.append("fadfadfadfafd1").append("dafdfdasfa2"));

        System.out.println("use time = " + (System.currentTimeMillis() - start));
    }

    public void cal(@NotNull String a, @NotNull String b, @NotNull String c) {
        System.out.println(a + b + c);
    }

}
