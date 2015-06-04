package com.gamesky.card.core;

import java.io.Serializable;

/**
 * 数据序列化接口
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public interface Marshaller<K extends Key, V extends Serializable> {

    public void marshal(K k, V v);

    public V unmarshal(K k);
}
