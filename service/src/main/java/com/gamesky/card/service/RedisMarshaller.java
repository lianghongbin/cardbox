package com.gamesky.card.service;

import com.gamesky.card.core.Cacheable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.SerializeUtils;
import com.gamesky.card.core.exceptions.MarshalException;
import redis.clients.jedis.Jedis;

import java.io.Serializable;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class RedisMarshaller<K extends Cacheable, V extends Serializable> implements Marshaller<K, V> {

    private Jedis jedis;
    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    @Override
    public void marshal(K k, V v) throws MarshalException {
        if (k.expire() == 0) {
            jedis.del(k.k().getBytes());
            return;
        }

        jedis.setex(k.k().getBytes(), k.expire(), SerializeUtils.serialize(v));
    }

    @SuppressWarnings("unchecked")
    @Override
    public V unmarshal(K k) throws MarshalException {

        byte[] value = jedis.get(k.k().getBytes());
        return (V) SerializeUtils.unserialize(value);
    }
}
