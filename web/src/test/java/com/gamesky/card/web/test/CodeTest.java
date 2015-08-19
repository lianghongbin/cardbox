package com.gamesky.card.web.test;

import com.gamesky.card.core.utils.CodeUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * lianghongbin on 15/8/19.
 */
public class CodeTest {

    @Test
    public void testFromPhone() {
        System.out.println(Long.toHexString(System.currentTimeMillis()));
        String hexCode = CodeUtils.fromPhone("13910661166");
        System.out.println(hexCode);

        Assert.assertTrue(true);
    }

    @Test
    public void testToPhone() {
        String phone = CodeUtils.toPhone("18A15FC26");
        System.out.println(phone);
        Assert.assertTrue(true);
    }
}
