package com.gamesky.card.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class LocalSessionMarshaller<K extends Key, V extends Serializable> implements Marshaller<K, V> {

    private int seconds = 24 * 60 * 60;    //默认缓存1天
    private int maxSize = 1000000;          //最高缓存100万数据
    private Cache<K, V> cache;
    private Callable<V> callable;
    private static Logger logger = LoggerFactory.getLogger(LocalSessionMarshaller.class);

    private LocalSessionMarshaller() {
        cache = CacheBuilder
                .newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(seconds, TimeUnit.SECONDS)
                .build();
    }

    private static class MarshallerHolder {
        static LocalSessionMarshaller instance = new LocalSessionMarshaller();
    }

    public static LocalSessionMarshaller getInstance() {
        return MarshallerHolder.instance;
    }

    public void setCallable(Callable<V> callable) {
        this.callable = callable;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void marshal(K k, V v) {
        cache.put(k, v);
    }

    @Override
    public V unmarshal(K k) {
        try {
            return cache.get(k, callable);
        } catch (ExecutionException e) {
            logger.error("cache get error:{}", e);
            return null;
        }
    }
}
