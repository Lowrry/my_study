package com.github.my_study.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/10<p/>
 * Time: 17:15<p/>
 */
public class CachePool<K, V> extends LinkedHashMap<K, CacheWraper<V>> {

    public CachePool(int capacity) {
        super();
        this.capacity = capacity;
    }

    // 容量,也就是最大的key-value数
    int capacity;
    // 维护每个缓存对象的最新更新时间 lastUpdateTime
    // 每次增删改查都会触发删除过期的缓存

    //1.LRU
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, CacheWraper<V>> eldest) {
        // 这里计算的应该是有效的entry数,无效的不能算
        // 我们只要在对缓存有任何操作的时候,先触发对缓存的清理
        return this.size() >= capacity;
    }

    //2.访问缓存
//    public V getValue(K key) {
//        V v = this.get(key);
//    }
//
//    private void clearTimeOut() {
//        long current = System.currentTimeMillis();
//        for (Map.Entry<K, CacheWraper<V>> entry : this.entrySet()) {
//            if(entry)
//        }
//    }

}