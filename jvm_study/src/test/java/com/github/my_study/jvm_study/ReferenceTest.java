package com.github.my_study.jvm_study;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.lang.ref.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 弱引用/软引用/虚拟引用
 * 1. 软引用 - Android客户端利用这个可以避免内存溢出问题;
 * 2. 软引用服务端也可以用来做缓存; 感觉比较适合短期的一个缓存, 因为如果内存不够了, 软引用就会全部被回收了.
 * 3. 弱引用 - 作者解释说可以用来做标准的map, ThreadLocal就是利用弱引用实现的.
 * 4. 虚拟引用能干什么呢
 *
 * DirectByteBuffer对象在创建的时候关联了一个PhantomReference，说到PhantomReference它其实主要是用来跟踪对象何时被回收的，
 * 它不能影响gc决策，但是gc过程中如果发现某个对象除了只有PhantomReference引用它之外，并没有其他的地方引用它了，
 * 那将会把这个引用放到java.lang.ref.Reference.pending队列里，在gc完毕的时候通知ReferenceHandler这个守护线程去执行一些后置处理，
 * 而DirectByteBuffer关联的PhantomReference是PhantomReference的一个子类，
 * 在最终的处理里,会通过Unsafe的free接口来释放DirectByteBuffer对应的堆外内存块
 */
public class ReferenceTest {

    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null) {
            System.out.println("in Queue : " + inq.get());
        }
    }

    // 测试软引用,弱引用,虚拟引用什么时候回收
    @Test
    public void testGC() {
        int size = 10;
        System.out.println("--------Soft------------");                             // 软引用
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<>();
        for (int i = 0; i < size; ++i) {
            sa.add(new SoftReference<>(new VeryBig("soft" + i), rq));
            System.out.println("just created soft: " + sa.getLast().get());
            checkQueue();
        }

        System.out.println();
        System.out.println("--------Weak------------");                             // 弱引用
        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<>();
        for (int i = 0; i < size; ++i) {
            wa.add(new WeakReference<>(new VeryBig("weak" + i), rq));
            System.out.println("just create weak : " + wa.getLast().get());
            checkQueue();
        }

        System.gc();
        System.out.println();
        System.out.println("=======通知 GC=======");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("--------phantom------------");
        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<PhantomReference<VeryBig>>();             // 虚拟引用
        for (int i = 0; i < size; ++i) {
            pa.add(new PhantomReference<>(new VeryBig("phantom " + i), rq));
            System.out.println("just create Phantom : " + pa.getLast().get());
            checkQueue();
        }
    }

    // 模拟针对大对象进行缓存
    // -server -Xms4m -Xmx4m
    @Test
    public void testCache() {
        SoftReference<VeryBig> bigSoftReference = new SoftReference<>(new VeryBig("bigCache"));

        /**
         * 这里是直接将弱引用作为map的value, 在内存不够时被gc回收.
         * JDK中一个典型的弱引用的用法就是ThreadLocal.
         * 但ThreadLocal中是将Entry的key作为弱引用,
         * 在有强引用的线程Thread被回收后, key就没有强引用了,
         * 然后key被gc回收后, value由读写操作触发时主动清理. 这样避免内存溢出.
         */
        Map<String, SoftReference<VeryBig>> cache = new HashMap<>();
        cache.put("test-A", bigSoftReference);

        //第一次查询
        System.out.println();
        System.out.println("=======> First Query = " + cache.get("test-A").get());


        //继续在堆中创建,触发GC
        for (int i = 0; i < 300; i++) {
            SoftReference<VeryBig> tmp = null;
            try {
                tmp = new SoftReference<>(new VeryBig("bigCache" + i));
            } catch (Throwable t) {
                System.out.println("create SoftReference error : " + t.getMessage());
                t.printStackTrace();
            }

            // 缓慢点gc
//            try {
//                Thread.sleep(200);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            cache.put("test" + i, tmp);
        }

        //第二次查询
        System.out.println("=======> Second Query = " + cache.get("test-A").get());
        for (int i = 0; i < 300; i++) {
            SoftReference<VeryBig> s = cache.get("test" + i);
            System.out.println(s == null ? null : s.get());
        }
    }

    static volatile VeryBig staticBig;

    // 被包裹的对象有强引用时不允许回收
    @Test
    public void testExistStrongRef1() {
        WeakReference<VeryBig> reference = new WeakReference<>(new VeryBig("bigCache"));
        staticBig = reference.get();

        //第一次查询
        System.out.println("=======> First Query = " + print(reference));

        //第一次通知GC
        System.gc();

        //第二次查询
        System.out.println("=======> Second Query = " + print(reference));

    }

    String print(WeakReference<VeryBig> s) {
        if (s == null || s.get() == null) return null;
        return s.get().toString();
    }


    @Test
    public void testGuavaCache() throws InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .concurrencyLevel(10)
                .weakValues()
//                .softValues()
//                .weakKeys()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("Loading cache by key :" + key);
                        return key + "'s value";
                    }
                });

        System.out.println(cache.getUnchecked("AAA"));
        System.out.println(cache.getUnchecked("AAA"));

        System.out.println();
        System.gc();

        System.out.println(cache.getUnchecked("AAA"));
    }

    @Test
    public void testWhenGC(){
        //-Xms4m -Xmx4m
        for(int i=0;i <100000; i++){
            new VeryBig(i+"");
        }
    }

}

class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String id) {
        ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    /*
     * 即使在可达性分析算法中不可达的对象,也不是马上就死的.
     * 要宣告一个对象死亡.至少要经历两次标记过程:如果对象在可达性分析后发现没有与GC ROOTS相连的引用链,那么他将会被第一次标记.
     * 并进行一次筛选.筛选的条件是此对象有没有必要执行finalize方法,如果对象没有覆盖finalize方法或者finalize方法已经被执行过了.
     * 虚拟机将视为”没有必要执行”,如果有必要执行finalize方法,那么这个对象会被放入F-Queue队列中,
     * 并在稍后由虚拟机创建一个低优先级的Finalizer线程去执行他,这里的执行时值虚拟机会触发这个方法,但不承诺会等待他运行结束.
     * 稍后会进行第二次小规模标记.被标记两次的对象基本上他就是真的被回收了.
     */
    protected void finalize() {
        System.out.println("finalize...." + ident);
    }

}

