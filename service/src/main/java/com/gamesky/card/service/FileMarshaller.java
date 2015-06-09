package com.gamesky.card.service;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 文件存储实现类
 * Created on 6/9/15.
 *
 * @Author lianghongbin
 */
public class FileMarshaller<K extends Keyable, V extends Serializable> implements Marshaller<K, V> {

    @Override
    public void marshal(K k, V v) {

    }

    @Override
    public V unmarshal(K k) {
        return null;
    }
}
