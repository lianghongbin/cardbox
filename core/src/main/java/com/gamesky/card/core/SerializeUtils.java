package com.gamesky.card.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created on 6/11/15.
 *
 * @Author lianghongbin
 */
public class SerializeUtils {
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos;
        ByteArrayOutputStream bos;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            return bos.toByteArray();
        } catch (Exception ignored) {

        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bis;
        try {
            bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            return ois.readObject();
        } catch (Exception ignored) {
        }

        return null;
    }
}