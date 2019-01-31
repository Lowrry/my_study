package com.github.my_study;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: luxiaochun<p/>
 * Date: 2018/5/25<p/>
 * Time: 10:20<p/>
 */
public class SimpleFileTest {

    @Test
    public void analysisWorkTime() {
        String filePath = "e://calendar.jsp.html";

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件不存在! " + file.getPath());
            return;
        }

        FileInputStream fileInputStream = null;

        // todos BIO,NIO

        List<String> list = new ArrayList<>();
        int total = 0;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] bytes = new byte[10000];

            int flag = 0;
            LOOP:
            while (fileInputStream.read(bytes) != -1) {
                String waitHandle = new String(bytes);

                for (; ; ) {
                    int index = waitHandle.indexOf("卡");
                    // 直到找不到,继续下一次循环
                    if (index == -1) {
                        continue LOOP;
                    }

                    waitHandle = waitHandle.substring(index - 1);

                    String target = waitHandle.substring(0, 8);
                    list.add(target);
                    waitHandle = waitHandle.substring(8);

                    System.out.println(target);
                    if (++flag % 2 == 0) {
                        String start = list.get(list.size() - 2);
                        String[] split1 = start.split(":");
                        Integer startHour = Integer.valueOf(split1[0].substring(3));
                        Integer startMinute = Integer.valueOf(split1[1]);

                        String end = list.get(list.size() - 1);
                        String[] split2 = end.split(":");
                        Integer endHour = Integer.valueOf(split2[0].substring(3));
                        Integer endMinute = Integer.valueOf(split2[1]);

                        int tmp = endMinute - startMinute;
                        int minutes = tmp >= 0 ? tmp : (60 + tmp);
                        int hours = (tmp >= 0 ? 0 : -1) + endHour - startHour;

                        Integer oneDayMinutes = hours * 60 + minutes;

                        total+=oneDayMinutes;
                        System.out.println("出勤: " + hours + "小时" + minutes + "分钟");
                        System.out.println();
                    }
                }

            }

            System.out.println("-----------------------");
            System.out.println("总共:" + total +"分钟");
            System.out.println("总共:" + getHoursAndMinute(total));

            int totalDays = list.size() / 2;
            int standardTime = totalDays*9*60;

            System.out.println("实际出勤" + totalDays + "天, 多出工时:" + getHoursAndMinute(total-standardTime));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//        fileOutputStream.write();
    }

    private String getHoursAndMinute(int minutes){
        return minutes/60 + "小时" + (minutes - (minutes/60)*60) +"分钟";
    }

    @Test
    public void test2(){
        String s1 = "09";
        String s2 = "19";
        System.out.println(Integer.valueOf(s2) - Integer.valueOf(s1));
    }
}
