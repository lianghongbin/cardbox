package com.gamesky.card.service;

import com.gamesky.card.core.Keyable;
import com.gamesky.card.core.Marshaller;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * 文件存储实现类
 * Created on 6/9/15.
 *
 * @Author lianghongbin
 */
public class LocalFileMarshaller<K extends Keyable, V extends Serializable> implements Marshaller<K, V> {

    private final static Logger logger = LoggerFactory.getLogger(LocalFileMarshaller.class);

    @Override
    public void marshal(K k, V v) {

        File file = new File(k.k());
        try {
            FileUtils.writeByteArrayToFile(file, (byte[]) v);
        } catch (IOException e) {
            logger.error("存储文件失败：{}", e);
        }
    }

    @Override
    public V unmarshal(K k) {
        File file = new File(k.k());
        try {
            return (V) FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            logger.error("存储文件失败：{}", e);
        }
        return null;
    }
}
