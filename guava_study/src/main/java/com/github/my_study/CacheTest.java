package com.github.my_study;

import com.google.common.cache.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/4<p/>
 * Time: 14:18<p/>
 */
public class CacheTest {

    @Test
    public void test1() throws IOException, InterruptedException {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .expireAfterAccess(20, TimeUnit.SECONDS)
                .concurrencyLevel(10)   // 这里能设置并发的访问级别
                // 但是缓存不存在,所有的请求都穿透了缓存,访问数据库,这种情况也是不允许的
                // 提供不同的接口
                // 1. 是可以接受一定延时的,这种数据请求获取的数据,是有一定时间的缓存的. 缓存没有, 直接请求数据库, 并回写到缓存.
                // 2. 精确查询数据库的接口,有白名单限制的,需要申请调用,并对请求量进行评估的
                // 3. 所有的接口都可以提供打开和关闭缓存的开关,用来排查问题的
                // 4. 定时预加载的接口,接口内部会有定时任务,定时刷新缓存,预加载数据.保证调用查询时,速度比较快,这个适用于比较重量的数据结构,
                // 超时时间需要根据需要设置,每个数据体比较大,所以数据量不宜太多.
                // 5. 数据有修改,及时刷新的接口,缓存没有就直接返回空.
                // 6. 热点数据缓存, 某个数据连续集中被访问, 在本地异步生成本地堆缓存, 抗流量效果更好
                // 7. 对主站的多级缓存做优化
                // 8. 有时候,有些表有关联查询,有很复杂的大批量的查询. 很难维护数据一致性. 可以针对某一种场景进行数据一致性缓存.
                // 比如只针对根据产品id查询套餐的场景
                // 8. 写点小例子,测试下??

                // 对套餐的缓存能否做的更快些??
                // 特卖会的接口是不是可以好好优化下? 特卖的数据比较有限, 保证所有数据都从缓存获取, 有专门的redis集群, 主备集群

                .removalListener(new RemovalListener<Object, Object>() {
                    @Override
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println("I am removed " + notification);
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("init cache " + key);
                        return key + " - cache";
                    }

                });

        System.out.println("============");

        for (int i = 0; i < 20; i++) {
            String result = cache.getUnchecked("user-" + i);
            System.out.println("query result = " + result);
        }

        System.out.println("==============");

        for (int i = 0; i < 100; i++) {
            // cache的超时清理是依赖读取或者写入操作,读取或者写入时会清理超时失效的缓存.
            System.out.println(cache.getUnchecked("test"));
            Thread.sleep(1000);
        }

        System.in.read();

    }

}
