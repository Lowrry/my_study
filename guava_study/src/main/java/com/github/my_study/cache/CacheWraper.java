package com.github.my_study.cache;

/**
 * User: luxiaochun<p/>
 * Date: 2018/1/10<p/>
 * Time: 17:24<p/>
 */
public class CacheWraper<V> {

    V value;

    // 超时时间(毫秒)
    long timeOut = -1;

    public CacheWraper(V value) {
        this.value = value;
    }

    public CacheWraper(V value, long timeOut) {
        this.value = value;
        this.timeOut = timeOut;
    }
}
