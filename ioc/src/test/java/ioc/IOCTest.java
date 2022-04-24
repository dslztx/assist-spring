package ioc;

import java.util.Arrays;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import ioc.xml.A;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Slf4j
public class IOCTest {

    @Resource
    ApplicationContext applicationContext;

    @Resource
    A a;

    @Test
    public void aTest() {
        try {
            Assert.assertTrue(a.getAaBean() != null);
            Assert.assertTrue(a.getaKey().equals("java"));
            Assert.assertTrue(a.getAge() == 33);

            Assert.assertTrue(a.getIntList().equals(Arrays.asList(1, 2, 3)));
            Assert.assertTrue(a.getStrList().equals(Arrays.asList("hello", "world")));

            Assert.assertTrue(a.getIntSet().size() == 3);
            Assert.assertTrue(a.getStrSet().size() == 2);

            Assert.assertTrue(a.getStrIntMap().keySet().size() == 3);
            Assert.assertTrue(a.getStrStrMap().keySet().size() == 3);

            Assert.assertTrue(a.isIfDeleted() == true);
        } catch (Exception e) {
            log.error("", e);
            Assert.fail();
        }
    }

}
