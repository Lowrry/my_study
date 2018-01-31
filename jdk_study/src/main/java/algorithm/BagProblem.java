package algorithm;

import java.util.*;

/**
 * User: luxiaochun<p/>
 * Date: 2017/10/12<p/>
 * Time: 10:19<p/>
 */
public class BagProblem {

    //todo 总重量不能超过150,如果只顾贪婪的拿,到最后不够再拿了,也不行,是不是这个不是贪婪的比较好的场合

    public static void main(String[] args) {
        // 1.穷举法,就是找出所有的排列组合,看哪个组合价值高
        // 首先要找出符合条件的所有组合,一共有2^7种,里面需要排除超过150重量的
        // 这个就相当于一个7位的2进制数,遍历所有的数字,就相当于遍历出所有的组合了
        // todo 时间复杂度是指数级的 2^n
        // 其实算法不难,不是需要你去创造算法,你都是站在前人的肩膀上解决类似的问题,有点像设计模式,是不是?

        List<Goods> list = new ArrayList<>();
        list.add(new Goods("A", 35, 10));
        list.add(new Goods("B", 30, 40));
        list.add(new Goods("C", 6, 30));
        list.add(new Goods("D", 50, 50));
        list.add(new Goods("E", 40, 35));
        list.add(new Goods("F", 10, 40));
        list.add(new Goods("G", 25, 30));

        //选择的结果
        List<String> result = new ArrayList<>();

        long start = System.nanoTime();
        int maxWeight = 0;
        int maxValue = 0;

        Binary b = new Binary(2, 0);
        while (b.value < Math.pow(2, 7)) {
            b.add(1);
            char[] chars = b.get().toCharArray();

            int totalWeight = 0;
            int totalValue = 0;
            List<String> tmpResult = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                if ("1".equals(chars[i] + "")) {
                    Goods thisGoods = list.get(i);
                    if (thisGoods.weight + totalWeight > 150) {
                        break;
                    }
                    totalWeight += thisGoods.weight;
                    totalValue += thisGoods.value;
                    tmpResult.add(thisGoods.name);
                }
            }

            if (maxWeight < totalWeight) {
                maxWeight = totalWeight;
                maxValue = totalValue;
                result = tmpResult;
            }
        }

        System.out.println("Max weight = " + maxWeight + ", max value = " + maxValue);
        System.out.println("select result = " + result);
        System.out.println("finish use time = " + (System.nanoTime() - start));
        System.out.println("====================================================================");

        result.clear();

        // 2.贪婪算法,就是每次都取单位重量价值最高的物品
        // 排序的时间复杂度,快排是lg(n), 增长量曲线比指数级的好很多.后面的贪婪取,时间复杂度是n==> log(n) + n
        long start2 = System.nanoTime();
        Collections.sort(list, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {    //按单位重量的价值从小到大排序
                return o1.value * o2.weight - o2.value * o1.weight;
            }
        });

        int totalWeight = 0;
        int totalValue = 0;
        for (Iterator<Goods> iterator = list.iterator(); iterator.hasNext(); ) {    //从后往前遍历
            Goods maxValuableGoods = iterator.next();
            if (totalWeight + maxValuableGoods.weight > 150) {
                break;
            }
            totalWeight += maxValuableGoods.weight;
            totalValue += maxValuableGoods.value;
            result.add(maxValuableGoods.name);
        }

        System.out.println("Max weight = " + totalWeight + ", max value = " + totalValue);
        System.out.println("select result = " + result);
        System.out.println("finish use time = " + (System.nanoTime() - start2));
    }

    static class Goods {
        String name;
        int weight;
        int value;

        public Goods(String name, int weight, int value) {
            this.name = name;
            this.weight = weight;
            this.value = value;
        }
    }

}