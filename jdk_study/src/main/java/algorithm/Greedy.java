package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/3<p/>
 * Time: 17:48<p/>
 */
public class Greedy {

    /**
     * 贪心算法逻辑比较简单
     * 就是每次选择都是考虑当前场景下,做出最优的选择,
     * 最终获得的解集合正好是全局的最优解,所以他的适用场景是有限的.
     */
    // 一个简单的问题就是阶梯教室的使用时间问题,要求在一天中安排尽量多的活动.
    // 可以对活动 按结束时间从小到大进行排序
    // 安排活动时,尽量安排最早结束的活动,这样剩余的可利用时间是最大的
    // 最终得到的解集合也就是最优解,但是可能不是唯一的最优解.
    // 每个算法都是需要能用数学证明正确性的,这个可以用归纳法?

    // 首先有一个数组,每个元素代表了活动的开始结束时间
    static Activity[] as = new Activity[11];

    static {
        // 算法定义都是依赖基础数据类型:数组实现的,这个在各个语言里都有的
        as[0] = new Activity(1, 4);
        as[1] = new Activity(3, 5);
        as[2] = new Activity(0, 6);
        as[3] = new Activity(5, 7);
        as[4] = new Activity(3, 9);
        as[5] = new Activity(5, 9);
        as[6] = new Activity(6, 10);
        as[7] = new Activity(8, 11);
        as[8] = new Activity(8, 12);
        as[9] = new Activity(2, 14);
        as[10] = new Activity(12, 16);
    }

    // 写个例子验证一下
    // todo 同样的,用动态规划也实现这个问题看下
    // 贪心选择简单的地方在于,我们每次做出选择时,都不要从全局去判断选择一个最优解.
    // 动态规划算法的自顶向下,其实就是不停的寻找最优解,把最优解结果缓存下来,避免解决重复的问题.
    // 动态规划算法的自底向上,其实就是从比较小的问题入手,也要把结果缓存下来,这样后续问题的子问题都已经是解决过的了.

    // todo 贪心算法的递归形式实现一下

    // todo 分治算法理论看下? - fork/join例子实现一下

    // 算法书上的算法其实都是基于数组类型实现的,这个在各个语言中都有
    // 我自己实现的程序是利用java的面向对象方法实现的,这种实现方式更容易理解一些,有时候也更简单一些
    // 这里需要提到的一个注意点就是,递归算法需要考虑
    public static void main(String[] args) {
        // 1.排序
        Arrays.sort(as, new Comparator<Activity>() {
            @Override
            public int compare(Activity o1, Activity o2) {
                return o1.end - o2.end;
            }
        });


        // 2.对排序好的结果,进行求解.
        // 递归算法,要有循环的界限.
        // 这里贪心算法的界限就是时间都分配完了

        List<Activity> plans = new ArrayList<>();

        int current = 0;
        for (Activity a : as) {
            if(current >= 16) {
                break;
            }

            if (a.begin >= current) {
                plans.add(a);
                current = a.end;
            }
        }

        System.out.println(plans);


        // 贪心又分为2种算法, (1)递归贪心算法 (2) ?
        // 递归贪心算法,就是不停的获取当前最优解,直到结束时间完毕了为止
    }

    public static class Activity {
        int begin;
        int end;

        public Activity(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Activity{");
            sb.append("begin=").append(begin);
            sb.append(", end=").append(end);
            sb.append('}');
            return sb.toString();
        }
    }

}
