package com.github.my_study.map;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * User: luxiaochun<p/>
 * Date: 2018/3/13<p/>
 * Time: 16:23<p/>
 */
public class MapTest {

    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();

        System.out.println("==============start===============");

        for (int i = 0; i < 10000000; i++) {
            String s = i + "";
            map.put(s, s);
        }

        print("10", map);
        print("100", map);
        print("1000", map);
        print("10000", map);
        print("100000", map);
        print("1000000", map);
        print("10000000", map);

        System.in.read();
    }

    private static void print(String key, Map<String,String> map){
        long start = System.nanoTime();
        System.out.println(map.get(key));
        System.out.println("use time = " + (System.nanoTime() - start));
    }

}
