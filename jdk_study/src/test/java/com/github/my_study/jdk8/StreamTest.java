package com.github.my_study.jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * User: luxiaochun<p/>
 * Date: 2018/10/20<p/>
 * Time: 11:19<p/>
 */
public class StreamTest {

    @Test
    public void testForEach() {
        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g");

        list.stream().limit(6).forEach(System.out::print);
    }

    @Test
    public void testOrElse() {
        List<String> strings = prepareList();

        String max = strings.stream()
                .filter(s -> s != null)
                .peek(System.out::println)
                .max(Comparator.naturalOrder())
                .orElse("null");

        System.out.println("max = " + max);
    }

    private List<String> prepareList() {
        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        return strings;
    }

    // 1. map
    // 可以用于转化值.
    @Test
    public void test1() {
        List<String> list = prepareList();

        List<Long> result = list.stream().map(Long::valueOf).collect(Collectors.toList());
        System.out.println(result);
    }

    // 2. mapToInt
    @Test
    public void test2() {
        List<String> list = prepareList();
        list.stream().mapToInt(Integer::valueOf).forEach(System.out::println);
    }

    // 3. flatMap
    @Test
    public void test3() {
        List<Order> orders = getOrders();

        // 平铺, 其实就是把所有的stream都铺开来, 方便直接遍历
        // 而且这里内部没用用到遍历, 只是生成了包裹对象, 效率还是蛮高的.
        orders.stream().flatMap(order -> order.getItems().stream()).forEach(item -> System.out.println(item.getGoodsId()));
    }

    private List<Order> getOrders() {
        List<Order> orders = new ArrayList<>();

        for (Long k = 10L; k < 13L; k++) {
            Order order = new Order();
            order.setOrderId(k);
            orders.add(order);

            List<Item> items = new ArrayList<>();
            order.setItems(items);

            for (Long i = 1L; i < 5L; i++) {
                Item item = new Item();
                item.setGoodsId(i);
                items.add(item);
            }
        }
        return orders;
    }

    // flagMapToInt
    @Test
    public void test4() {
        List<Order> orders = getOrders();
        orders.stream().flatMapToInt(new Function<Order, IntStream>() {
            @Override
            public IntStream apply(Order order) {
                return order.getItems().stream().mapToInt(item -> Integer.valueOf(item.getGoodsId() + ""));
            }
        }).forEach(System.out::println);
    }

    // distinct
    @Test
    public void test5() {
        Stream.of("1", "1", "2").distinct().forEach(System.out::println);
    }

    // limit & skip
    @Test
    public void test6() {
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).limit(3).forEach(System.out::print);
        System.out.println();
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6}).skip(2).forEach(System.out::print);
    }


    // reduce
    @Test
    public void test7() {
        IntStream.of(1, 2, 3, 4).reduce(new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return left - right;
            }
        }).ifPresent(r -> System.out.print("result is " + r));
    }

    // collect
    @Test
    public void test8(){
        Stream<String> stream1 = Stream.of("1", "2", "3");
        System.out.println(stream1.count());

        Stream<String> stream2 = Stream.of("1", "2", "3");
        List<String> collect = stream2.collect(Collectors.toList());
        System.out.println(collect);
    }

    /*
    1. map
    2. mapToInt
    3. flatMap
    4. flatMapToInt
    5. distinct
    6. limit
    7. skip
    8. reduce
    9. collect
    10. min/max
    11. count
    12. anyMatch
    13. findFirst()

    14. concat
     */


    public static class Order {
        Long orderId;
        List<Item> items;

        public Long getOrderId() {
            return orderId;
        }

        public void setOrderId(Long orderId) {
            this.orderId = orderId;
        }

        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }

    public static class Item {
        Long goodsId;

        public Long getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(Long goodsId) {
            this.goodsId = goodsId;
        }
    }
}
