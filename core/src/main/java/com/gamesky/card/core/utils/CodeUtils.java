package com.gamesky.card.core.utils;

/**
 * lianghongbin on 15/8/19.
 */
public class CodeUtils {

    private final static int pos = 4;

    public static String fromPhone(String phone) {
        String tmp = phone.substring(1);
        tmp = tmp.substring(pos) + tmp.substring(0, pos);
        return Long.toHexString(Long.parseLong(tmp)).toUpperCase();
    }

    public static String toPhone(String hexCode) {
        long num = Long.parseLong(hexCode, 16);
        String tmp = String.valueOf(num);
        if (tmp.length() < 10) {
            tmp = "0" + tmp;
        }

        return "1" + tmp.substring(10-pos) + tmp.substring(0,10-pos);
    }
}
