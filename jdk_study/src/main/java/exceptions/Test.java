package exceptions;

/**
 * User: luxiaochun<p/>
 * Date: 2017/7/3<p/>
 * Time: 14:09<p/>
 */
public class Test {

    public static void main(String[] args) throws Exception {
        System.out.println(getResult());
    }

    static String getResult() throws Exception {
        try {
            return 1 / 0 + "";
        } catch (Exception e) {
            System.out.println(e.toString());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement);
            }
//            throw e;
        }
            throw new IllegalArgumentException("111");
    }

}
