package com.gamesky.card.service;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created on 6/17/15.
 *
 * @Author lianghongbin
 */
public class BeanUtils {

    public static Map beanToMap(Object object) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Map param = org.apache.commons.beanutils.BeanUtils.describe(object);
        param.remove("class");
        return param;
    }
}
