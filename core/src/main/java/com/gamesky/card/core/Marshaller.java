package com.gamesky.card.core;

import com.gamesky.card.core.exceptions.MarshalException;

import java.io.Serializable;

/**
 * 数据序列化接口
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public interface Marshaller<K extends Keyable, V extends Serializable> {

    public void marshal(K k, V v) throws MarshalException;

    public V unmarshal(K k) throws MarshalException;
}
