package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 递归
 * User: luxiaochun<p/>
 * Date: 2017/10/16<p/>
 * Time: 14:37<p/>
 */
public class Recursion {

    // todo 动态规划,为什么自底向上更优?
    // todo 为什么20这么快就计算好了?
    // todo 前面都考虑错了,应该用没有上限的那种场景.
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        /*
         * 递归方法,每次都是继续对剩余部分进行切割.
         */
        final int LENGTH = 25;

        Steel steel = new Steel();
        steel.cut(LENGTH, 0, new ArrayList<Integer>());

        System.out.println("max value :" + steel.getMaxValue() + ", cut plan : " + steel.getCutPlans()
                + ", cut times :" + Price.getCutTimes() +
                ", use time (seconds) : " + (System.currentTimeMillis() - start)/1000);
    }

    static class Steel {
        int maxValue = 0;
        List<Integer> cutPlans;

        public int getMaxValue() {
            return maxValue;
        }

        public List<Integer> getCutPlans() {
            return cutPlans;
        }

        /**
         * 递归方法进行切割
         *
         * @param totalLeft  剩余总长度
         * @param totalValue 当期累加值
         * @param totalList  当前切割的每段长度
         */
        public void cut(final int totalLeft, final int totalValue, final List<Integer> totalList) {     // 递归,计算出所有切割组合的总价格.
            // todo 确实算法在考虑边界问题的时候会有点小麻烦,比如这个问题的整个切割.

            if (totalLeft <= 10) {
                int oneValue = Price.getValue(totalLeft);    // 一整个不切分的价格
                List<Integer> oneList = new ArrayList<>(totalList);
                oneList.add(totalLeft);
                compareAndSet(totalValue + oneValue, oneList);
            }

            // 递归是每一层相互不影响的,第一层递归是就是第一次切几寸长度,第二层递归就是第二次切几寸长度, ... (每次都是在剩下的left长度基础上切分的)
            // 现在的问题, 第一层没有继续走下去, 或者第一层的多次遍历相互影响了. 这里是问题所在. 每一层for循环使用的left不能修改,入参的left,只能使用,不能修改.
            // 还有个问题, 就是多次遍历的list, 也在一个循环里面相互影响了, 怎么修改这个问题呢?

            // 新问题是好像已经获取到最优值了,但是没有结束.

            for (int i = 1; i <= totalLeft; i++) {              // 下面三个值只在多层递归调用之间做值传递,入参的值不做修改.
                int left = totalLeft;                           // for循环里面的操作,不能修改totalLeft的值.
                int value = totalValue;                         // for循环里面的操作,不能修改totalValue的值.
                List<Integer> list = new ArrayList<>(totalList);// for循环里面的操作,不能修改totalList的值.

                if (left - i < 0) {             // 直接忽略.
                    break;
                }

                left -= i;
                list.add(i);

                value += Price.getValue(i);
                if (left == 0) {                // 切分完毕,后续切割长度i会大于当前i,所以需要break跳出循环,不继续切割了.
                    compareAndSet(value, list);
                    break;
                }

                cut(left, value, list);        // 如果上面left剩余不为0,则需要递归调用,继续切分.
                // todo 其实这里的递归,就是类似深度优先搜索的算法.一直切割到不能切割为止.
            }
        }

        // 动态规划就是把反复求解的子问题缓存起来
        // 比较下动态规划求最优解的速度

        private void compareAndSet(int totalValue, List<Integer> list) {
            if (this.maxValue < totalValue) {
                this.maxValue = totalValue;
                this.cutPlans = list;
            }
        }

    }

}
