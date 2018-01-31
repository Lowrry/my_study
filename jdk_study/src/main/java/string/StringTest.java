package string;

import com.sun.management.OperatingSystemMXBean;
import org.junit.Test;

import java.lang.management.ManagementFactory;

/**
 * User: luxiaochun<p/>
 * Date: 2017/11/6<p/>
 * Time: 15:33<p/>
 */
public class StringTest {

    @Test
    public void testFormat() {
        Double cpuRate = 0.6;
        Double maxCpuTimeRate = 0.8;
        System.out.println("Pause Pull, CPU USAGE is " + String.format("%.2f", cpuRate) + "% >= " + String.format("%.2f", maxCpuTimeRate) + "%");
    }

    @Test
    public void print() throws InterruptedException {
        OperatingSystemMXBean OperatingSystem = ((OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean());

        long cpuTime1 = OperatingSystem.getProcessCpuTime();
        long upTime2 = ManagementFactory.getRuntimeMXBean().getUptime();

        System.out.println(10 / 2 * 5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                for(;;){
                    i++;
                }
            }
        }).start();
        Thread.sleep(1000);


        long cpuTime = OperatingSystem.getProcessCpuTime() - cpuTime1;
        long upTime = ManagementFactory.getRuntimeMXBean().getUptime() - upTime2;

//        this.lastCPUTime = cpuTime;
//        this.lastCPUUpTime = upTime;
//        if (elapsedTime <= 0L) {
//            return new BigDecimal(0);
//        }
//        float cpuUsage = (float)elapsedCpu / ((float)elapsedTime * 1000000.0F * this.OperatingSystem.getAvailableProcessors()) * 100.0F;
//        BigDecimal cpuRate = new BigDecimal(cpuUsage, new MathContext(4));
//
//        return cpuRate;

        System.out.println("cpuTime = " + cpuTime + ", upTime = " + upTime);
    }

    @Test
    public void testContain() {
        System.out.println("12345,56".contains("567"));
    }

    //todo linux test
    // 打成一个可执行的jar包直接执行? spring-boot或者是简单的测试程序,怎么执行jar包?
    @Test
    public void testIntern() {
        int i=0;
        for(;;){
            long start = System.nanoTime();
            String s = (++i+"dfadajr3iur3iu93r3j3jr393r9").intern();
            System.out.println(s + " use time = " + (System.nanoTime() - start));
        }
    }

    @Test
    public void test2(){
        String s = "111,";
        String[] split = s.split(",");
        for (String ss : split) {
            System.out.println(ss);
        }
        System.out.println("end");
    }

}
