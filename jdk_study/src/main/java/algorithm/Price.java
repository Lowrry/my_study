package algorithm;

/**
 * 价格
 * User: luxiaochun<p/>
 * Date: 2017/10/16<p/>
 * Time: 14:36<p/>
 */
public class Price {

    private static int cutTimes = 0;

    private int[] values = new int[]{
            1, 5, 8, 9, 10, 17, 17, 20, 24, 30,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60,
            40, 25, 38, 19, 40, 40, 27, 55, 34, 60

            // ... ...
    };

    static int getValue(int per) {

        cutTimes++;

        switch (per) {
            case 1:
                return 1;
            case 2:
                return 5;
            case 3:
                return 8;
            case 4:
                return 9;
            case 5:
                return 10;
            case 6:
                return 17;
            case 7:
                return 17;
            case 8:
                return 20;
            case 9:
                return 24;
            case 10:
                return 30;
            case 11:
                return 40;
            case 12:
                return 25;
            case 13:
                return 38;
            case 14:
                return 19;
            case 15:
                return 40;
            case 16:
                return 40;
            case 17:
                return 27;
            case 18:
                return 55;
            case 19:
                return 34;
            case 20:
                return 60;
            default:
                return 0;

            //无穷 ... ...
        }
    }

    public static int getCutTimes() {
        return cutTimes;
    }

    public static void clear() {
        cutTimes = 0;
    }
}
