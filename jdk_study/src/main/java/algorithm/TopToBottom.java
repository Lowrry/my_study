package algorithm;

import java.util.*;

/**
 * 他这个问题就是每一个长度都能对应一个价格,所以前面的问题要重新理解了,没有我们所设想的那种大于n的情况了
 * 这种问题就是一种不确定的问题,没有上限
 * <p>
 * 这依然还是一个遍历算法, 但是加入了带备忘的功能.
 * 时间负责度:
 * 每一次第一次遇到一个新问题,需要计算他的最优解,
 * 1 ~ n 需要全盘遍历下来有2^(n-1)种可能性 遍历的复杂度,这是指数级别的
 * <p>
 * 自顶向下,好像不需要全盘遍历吧
 * 先 1+(n-1) /
 * <p>
 * todo totalLeft大于10时,其实就化解为一个10以内的最优化求解问题,依赖这个最优解可以把totalLeft进行切分--> 这是错误的
 * todo 问题应该是化解为对针对1 ~ n求最优解的问题,
 * 2 = 2 / 1+1
 * 3 = 3 / 1+2 / 1+1+1
 * 4 = 4 / 1+3 / 2+2 / 1+1+2
 * 5 = 5 / 1+4 / 2+3 / 1+1+3 / 1+2+2 / 1+1+1+2 / 1+1+1+1+1
 * ...
 * 11 = 1+10 / 2+9 / 3+8 / 4+7 / 5+6
 * <p>
 * 5 = 5 / 1+4的最优解 / 2的最优解+3的 / ...
 * <p>
 * n = n / 1+(n-1) / 2+(n-2) / 3+(n-3) / ... / 10+(n-10)
 * 每次新遇到一个问题, 所以就是计算 1 ~ 10的最优解
 * 后续只是从这面查出结果
 * <p>
 * 转化为左边不切割,只对剩余部分切割的问题
 * 这个过程除了5的计算,其余计算都是对前面的进行查询, 复杂度 = O(1) * (n-1)/2
 * 倒是每一次遇到一个新的问题时,需要计算对应的复杂度
 * ... ...
 * 依次类推,针对n,其实前面n-1 ~ 1的最优解都已经求过了,会有很多公共的子问题
 * <p>
 * todo 如果totalLeft小于10就是下面的方法了
 * <p>
 * <p>
 * 带备忘的自顶向下的动态规划算法
 * 也是递归,但是把公共的子问题的求解结果保存起来;
 * 因为递归算法, 都是从顶向下的求解各个长度的最优解,第一次计算的时候把问题的解保存下来
 * <p>
 * User: luxiaochun<p/>
 * Date: 2017/10/16<p/>
 * Time: 14:37<p/>
 */
public class TopToBottom {

    // todo 动态规划,为什么自底向上更优?
    public static void main(String[] args) {
        final int LENGTH = 30;

        long start1 = System.nanoTime();

        Steel steel = new RecursionSteel();
        Result result = steel.cut(LENGTH);

        System.out.println("Steel Length = " + LENGTH + ", max value :" + result.value + ", cut plan : " + result.cutPlans
                + ", invoke times :" + Price.getCutTimes() +
                ", use time : " + (System.nanoTime() - start1)/1000);

        //=======================================

        Price.clear();

        long start2 = System.nanoTime();

        steel = new TopToBottomSteel(LENGTH);
        result = steel.cut(LENGTH);

        System.out.println("Steel Length = " + LENGTH + ", max value :" + result.value + ", cut plan : " + result.cutPlans
                + ", invoke times :" + Price.getCutTimes() +
                ", use time : " + (System.nanoTime() - start2)/1000);

        //=======================================
        Price.clear();

        long start3 = System.nanoTime();

        steel = new BottomToTop();
        result = steel.cut(LENGTH);

        System.out.println("Steel Length = " + LENGTH + ", max value :" + result.value + ", cut plan : " + result.cutPlans
                + ", invoke times :" + Price.getCutTimes() +
                ", use time : " + (System.nanoTime() - start3)/1000);


    }

    static class TopToBottomSteel implements Steel {
        public TopToBottomSteel(int length) {
            this.caches = new Result[length+1];
        }
        //用来做缓存的,也可以用数组来实现
        Result[] caches;

        /**
         * cut方法,返回length的最优解result
         * 参数如下
         * 1.待截取的长度
         */
        public Result cut(int length) {
            //这是递归方法的一个边界条件
            //其实这个就是在递归或者循环里面做了一个优化,用空间换时间,但是这种方法具有一个广泛的意义,所以定义为一个算法了,我是这样认为的
            if (caches[length] != null) {
                return caches[length];
            }

            //这也是一个边界条件,说明截取完毕了
            if (length == 0) {
                return new Result();
            }

            /* 下面就是递归调用cut方法 */

            //这里需要循环遍历,取出最优解
            Result best = new Result();
            for (int i = 1; i <= length; i++) {

                //继续计算剩余的最优解
                Result result = cut(length - i);    //对剩余部分求解

                int total = Price.getValue(i) + result.value;

                //result里是里面最优解的值
                if (best.value < total) {
                    //最优解, 这个是for循环的最大值
                    best.value = total;

                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.addAll(result.cutPlans);
                    best.cutPlans = tmp;
                }
            }

            //for循环一遍,会得到一个最优解,缓存下来
            caches[length] = best;

            //返回最优解
            return best;
        }
    }

    //todo 1.完成
    //todo 2.来个xiti
    //这个不是递归方法,其实和上面差不多,还是利用子问题来求解
    static class BottomToTop implements Steel {

        @Override
        public Result cut(int length) {
            Result[] caches = new Result[length];
            caches[0] = new Result();

            for (int i = 1; i <= length; i++) { //从steel长度为1开始求解,当到length问题时,子问题都已经求解完毕了

                Result best = new Result(); //最优解
                for (int j = 1; j <= i; j++) {  //求解从1
                    Result left = new Result(Price.getValue(j), Collections.singletonList(j));
                    Result right = caches[i - j];

                    if (left.value + right.value > best.value) {
                        best.value = left.value + right.value;

                        best.cutPlans.clear();
                        best.cutPlans.addAll(left.cutPlans);
                        best.cutPlans.addAll(right.cutPlans);
                    }
                }

                if (i == length) {
                    return best;
                }

                //最优解缓存到caches中
                caches[i] = best;
            }
            return null;    //不会到这里
        }

    }

    //普通递归算法
    static class RecursionSteel implements Steel {

        /**
         * cut方法,返回length的最优解result
         * 参数如下
         * 1.待截取的长度
         */
        public Result cut(int length) {

            //这是一个边界条件,说明截取完毕了
            if (length == 0) {
                return new Result();
            }

            /* 下面就是递归调用cut方法 */

            //这里需要循环遍历,取出最优解
            Result best = new Result();
            for (int i = 1; i <= length; i++) {

                //继续计算剩余的最优解
                Result result = cut(length - i);    //对剩余部分递归求解

                int total = Price.getValue(i) + result.value;

                //result里是里面最优解的值
                if (best.value < total) {
                    //最优解, 这个是for循环的最大值
                    best.value = total;

                    best.cutPlans.clear();
                    best.cutPlans.add(i);
                    best.cutPlans.addAll(result.cutPlans);
                }
            }

            //返回最优解
            return best;
        }
    }


    /**
     * 有几步是很关键的
     * 1. 递归方法的入参选择:
     * 因为是递归方法,方法不可能自己调用自己一直调用下去,那么就死循环了.
     * 遍历方法的结束点就是指针指向最后一个,就结束遍历了
     * 递归方法,需要找到一个临界点,我们这个问题的临界点就是steel剩余长度为0了,这时候递归方法停止调用!
     *
     * 这样很显然,剩余长度就是递归调用的参数
     *
     * 2.求解最优或者最大问题,至少肯定是要遍历一次的,不然怎么能知道哪个是最优的,重要的是减少遍历的次数,
     * 绝对要避免指数级增长的循环或者递归, 因为随着参数的变大, 风险太大
     *
     * 3.递归的思想其实和深度优先搜素的思想是一致的,先往深度去找到一个结果
     * 我们这个问题其实就是像一个二叉树,需要遍历树的每个节点,找到最优的一个分支
     *
     * 4.递归举个例子,就是Spring的在获取单例bean时,也是这种递归方法.
     * 因为我们获取一个bean时,他里面需要自动注入其他的bean,如果类的关系很复杂的话,这就是一个树状图
     * A依赖B,B依赖C,C依赖D... ...
     * 我们创建A时,需要创建B ...,直到依赖的bean都创建完毕为止,递归结束
     * 递归方法要注意的就是死循环问题, 在spring中就是循环依赖的问题, spring在创建单例bean时,
     * 会把当前正在创立的bean放到一个set中,如果后面遇到set中有的,说明死循环了.
     *
     * 如果某个bean已经创建了,会把bean缓存起来,下次获取的时候直接拿.
     * 虽然spring不是为了求问题的最优解,只是为了创建所有的bean,但是处理问题的思路是一致的.
     */

    /**
     * 子问题求解时间与射出的边数目成正比
     * 子问题的数目等于子问题图的节点数
     */
}
