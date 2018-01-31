package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 动态规划
 * <p>
 * User: luxiaochun<p/>
 * Date: 2017/10/13<p/>
 * Time: 17:07<p/>
 */
public class DynamicProgramming {

    public static void main(String[] args) {
        int length = 4;
        List<Integer> list = new ArrayList<>();

        // 递归,找出最大的价格

        /**
         * 截取1后, 剩余的继续进行各种截取, 这样可以递归出所有的组合, 需要求出最大的值.
         * 用for循环是不能实现的,这种不确定的组合问题, 需要递归?
         *
         * 1. 左边第一段截取的长度,截取好了以后就不做截取了,这样把递归的问题就变成了只剩下一边的递归问题
         * 2. 递归算法,把所有的子问题缓存下来,是否就是动态规划了??
         * 3. 每次截取的长度肯定在1-10之间
         *    当然需要考虑如果length小于n,那么肯定是要把n种情况都遍历出来的,就是左边截取1 ~ length 的最优场景都要考虑
         *    如果length大于n,那么就是求解左边截取1 ~ n情况下的最优解
         * 4. 带备忘的自定向下法 / 自底向上法
         *
         */

        int maxValue = 0;

        int currentValue = 0;
        int left = length;
        for (int i = 0; i < left; i++) {
//            Steel cut = new Steel(currentValue, left, i).invoke();
        }

        // 这种和常规的遍历不一样
        // 这是需要,深度优先的,一直把长度都截取完才行.

    }

    /**
     * 长度:1	2	3	4	5	6	7	8	9	10
     * 价格:1    5	8	9	10  17	17	20	24	30
     */
    static int getValue(int per) {
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
            default:
                return 0;
        }
    }

}
