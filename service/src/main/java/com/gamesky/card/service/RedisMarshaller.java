package com.gamesky.card.service;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import com.gamesky.card.core.SerializeUtils;
import com.gamesky.card.core.exceptions.MarshalException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.Serializable;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class RedisMarshaller<K extends Keyable, V extends Serializable> implements Marshaller<K, V> {

    private RedisTemplate<K, V> redisTemplate;

    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void marshal(K k, V v) throws MarshalException {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key  = serializer.serialize(k.k());
                byte[] name = SerializeUtils.serialize(v);

                connection.setEx(key, k.expire(), name);
                return true;
            }
        });
    }

    @Override
    public V unmarshal(K k) throws MarshalException {

        return redisTemplate.execute(new RedisCallback<V>() {
            public V doInRedis(RedisConnection connection)
                    throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] key = serializer.serialize(k.k());
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }

                return (V) SerializeUtils.unserialize(value);
            }
        });
    }
}
