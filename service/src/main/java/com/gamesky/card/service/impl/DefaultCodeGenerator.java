package com.gamesky.card.service.impl;

import com.gamesky.card.service.CodeGenerator;

import java.util.Random;

/**
 * Created on 6/5/15.
 *
 * @Author lianghongbin
 */
public class DefaultCodeGenerator implements CodeGenerator {

    @Override
    public String generate() {
        Random random = new Random(System.currentTimeMillis());
        int result = Math.abs(random.nextInt()) % 1000000;    //6位验证码
        return String.valueOf(result);
    }
}
