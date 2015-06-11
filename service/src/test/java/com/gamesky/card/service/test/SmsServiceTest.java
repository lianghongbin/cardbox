package com.gamesky.card.service.test;

import com.gamesky.card.service.CheckCodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created on 3/26/15.
 *
 * @Author lianghongbin
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml", "classpath:spring-dao.xml"})
public class SmsServiceTest extends AbstractJUnit4SpringContextTests {
    @Autowired
    private CheckCodeService smsService;

    @Test
    public void testSend() {
        //18611288996
        boolean result = smsService.send("13910661166", "123456");
        Assert.assertTrue(result);
    }
}