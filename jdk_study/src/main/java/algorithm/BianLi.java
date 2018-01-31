package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BianLi {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        final int LENGTH = 40;

        // 一直切割,直到剩下的长度不不可切割(包括不再切割的情况)
        /**
         * 每次都切成1/2/3 ...
         * 每次切割都可能会切成1,2,3 ...长度的, 也就是每个长度的节点处都可能会切割或者不切, 这样的节点有n-1个, 所以总的可能数为 2^(n-1)
         * 111111111/ 1111110/ 1 / 10 / 11
         * 从这个角度去遍历会简单些
         * 每个节点处可能不能用循环来做
         * 比较合适的做法还是用位来做,就是做成多少位的二进制数,0和1表示是否切分
         *
         * 这种穷举的方法, 时间复杂度: O(2^n)
         */
        int maxValue = 0;
        List<Integer> cutPlan = new ArrayList<>();

        Binary b = new Binary(2, 0);
        while (b.value < Math.pow(2, LENGTH - 1)) {     //todo 2^(n-1)
            System.out.println("come to here ...");

            b.add(1);
            char[] chars = b.get().toCharArray();

            List<Integer> cutList = new ArrayList<>();
            int max = 0;
            int begin = 0;
            int current = 0;

            int lastCut = 0;
            for (int i = chars.length - 1; i >= 0; i--) {   //todo 小于LENGTH的常量
                current++;
                char c = chars[i];

                if ('1' == c) {
                    int cut = current - begin;
                    if (cut < lastCut) {   //todo 保证cut长度是递增的,可以减少循环; 很多时候算法的优化就在这些细节方面, 增加变量, 减少循环 (以空间换时间)
                        break;
                    } else {
                        lastCut = cut;
                    }

                    max += Price.getValue(cut);
                    cutList.add(cut);

                    begin = current;
                }

                // 需要计算最后剩下来的一段
                if (i == 0) {
                    int cut = LENGTH - begin;

                    if (cut != 0) {
                        max += Price.getValue(cut);
                        cutList.add(cut);
                    }

                }
            }

            if (max > maxValue) {
                maxValue = max;
                cutPlan = cutList;
            }
        }

        // 考虑一整个,一点都不切的情况
        if (LENGTH <= 10) {
            int oneValue = Price.getValue(LENGTH);
            if (maxValue < oneValue) {
                maxValue = oneValue;
                cutPlan = Arrays.asList(0);
            }
        }

        System.out.println("max value :" + maxValue + ", cut plan : " + cutPlan + ", cut times :" + Price.getCutTimes() +
                ", use time : " + (System.currentTimeMillis() - start));
    }

}
