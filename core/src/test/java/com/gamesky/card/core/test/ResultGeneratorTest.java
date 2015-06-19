package com.gamesky.card.core.test;

import com.gamesky.card.core.Page;
import com.gamesky.card.core.ResultGenerator;
import com.gamesky.card.core.ReturnCode;
import com.gamesky.card.core.model.TestElement;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class ResultGeneratorTest {

    @Test
    public void testGeneratorError() {
        String result = ResultGenerator.generateError(ReturnCode.GENERAL);

        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGenerator() {
        TestElement element = new TestElement();
        element.setId(1);
        String result = ResultGenerator.generate(element);

        System.out.println(result);
        Assert.assertNotNull(result);
    }

    @Test
    public void testGeneratorPage() {
        TestElement element = new TestElement();
        element.setId(1);
        Page page = new Page(20, 10, 2);
        String result = ResultGenerator.generate(page, element);

        System.out.println(result);
        Assert.assertNotNull(result);
    }
}
