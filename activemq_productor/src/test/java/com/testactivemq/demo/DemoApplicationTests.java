package com.testactivemq.demo;

import com.testactivemq.DemopApplication;
import com.testactivemq.Productor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

@SpringBootTest(classes = DemopApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
class DemoApplicationTests {
    @Resource
    private Productor productor;

    @Test
    public void testSend(){
        productor.productMsg();
    }
}
