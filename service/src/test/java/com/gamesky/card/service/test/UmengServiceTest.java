package com.gamesky.card.service.test;

import com.gamesky.card.core.PushPayload;
import com.gamesky.card.service.PushService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * lianghongbin on 15/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml", "classpath:spring-dao.xml"})
public class UmengServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private PushService<PushPayload> pushService;

    @Test
    public void testUmeng() {
        String haoRong = "AsUk3F1GOlZ5pH201PTMxwye_UhOhBqx_Nm5s-Bhm1Ra";
        PushPayload pushPayload = new PushPayload(haoRong, "礼包大全推送测试标题", "礼包大全推送内容,看看成功不");

        try {
            pushService.push(pushPayload);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertTrue(true);
    }
}
