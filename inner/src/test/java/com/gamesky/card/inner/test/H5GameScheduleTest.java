package com.gamesky.card.inner.test;

import com.gamesky.card.inner.controller.H5GameSchedule;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * lianghongbin on 15/8/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-service.xml", "classpath:spring-dao.xml", "classpath:spring-mvc.xml"})
public class H5GameScheduleTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private H5GameSchedule schedule;

    @Test
    public void testFetch() {
        schedule.fetch();
        Assert.assertTrue(true);
    }
}