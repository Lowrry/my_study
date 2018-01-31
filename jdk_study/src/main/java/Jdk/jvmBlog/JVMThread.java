package Jdk.jvmBlog;

import com.sun.management.OperatingSystemMXBean;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * @author Robert HG (254963746@qq.com) on 9/15/15.
 */
@SuppressWarnings("restriction")
public class JVMThread {

    private volatile long lastCPUTime;              //volatile 在只有一个线程修改,其他线程都是读取的情况下是线程安全的!
    private volatile long lastCPUUpTime;
	private OperatingSystemMXBean OperatingSystem;
    private RuntimeMXBean Runtime;

    private static final JVMThread instance = new JVMThread();  //这里是一个静态变量,所以所有的线程都是共享同一个变量

    public static JVMThread getInstance() {
        return instance;
    }

    private ThreadMXBean threadMXBean;

	private JVMThread() {
        threadMXBean = ManagementFactory.getThreadMXBean();
        OperatingSystem = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        Runtime = ManagementFactory.getRuntimeMXBean();

        try {
            lastCPUTime = OperatingSystem.getProcessCpuTime();
        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
        }
    }

//    @Override
    public BigDecimal getProcessCpuTimeRate() {
        /**
         * JVMMonitor.getAttribute(JVMConstants.JMX_JVM_THREAD_NAME, "ProcessCpuTimeRate")会调用getProcessCpuTimeRate()方法.
         * 这个方法也只有一个线程会调用,就是com.github.ltsopensource.tasktracker.support.JobPullMachine#isMachineResEnough()方法,
         * 这里需要每秒获取一次,用于判断当前节点系统资源是否充足的.
         * lts-admin里的监控都是每分钟收集一次数据, 是由com.github.ltsopensource.core.monitor.MStatReportWorker完成的
         *
         * todo 但是单线程调用的应该不会有线程安全问题,为什么原来的监控是错误的呢? 作者也没反应这块有问题的.
          */
        long cpuTime = OperatingSystem.getProcessCpuTime();
        long upTime = Runtime.getUptime();

        long elapsedCpu = cpuTime - lastCPUTime;
        long elapsedTime = upTime - lastCPUUpTime;

        lastCPUTime = cpuTime;
        lastCPUUpTime = upTime;

        BigDecimal cpuRate;
        if (elapsedTime <= 0) {
            return new BigDecimal(0);
        }

        float cpuUsage = elapsedCpu / (elapsedTime * 10000F);
        cpuRate = new BigDecimal(cpuUsage, new MathContext(4));

        return cpuRate;
    }

    public BigDecimal getProcessCpuTimeRate2()
    {
        long cpuTime = this.OperatingSystem.getProcessCpuTime();
        long upTime = this.Runtime.getUptime();

        long elapsedCpu = cpuTime - this.lastCPUTime;
        long elapsedTime = upTime - this.lastCPUUpTime;

        this.lastCPUTime = cpuTime;
        this.lastCPUUpTime = upTime;
        if (elapsedTime <= 0L) {
            return new BigDecimal(0);
        }
        float cpuUsage = (float)elapsedCpu / ((float)elapsedTime * 1000000.0F * this.OperatingSystem.getAvailableProcessors()) * 100.0F;
        BigDecimal cpuRate = new BigDecimal(cpuUsage, new MathContext(4));

        return cpuRate;
    }

    public static void main(String[] args) throws IOException {
	    for(int i=0;i<2;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(;;){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(JVMThread.getInstance().getProcessCpuTimeRate2());
                    }
                }
            }).start();
        }

        for(int i=0;i<4;i++){

	        new Thread(new Runnable() {
                @Override
                public void run() {
                    Long j = Long.MIN_VALUE;
                    for(;;){
                        j++;
                    }
                }
            }).start();
        }

        System.in.read();

    }


}
