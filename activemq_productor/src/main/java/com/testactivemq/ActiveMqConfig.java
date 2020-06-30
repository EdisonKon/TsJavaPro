package com.testactivemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-29 14:39
 * @from
 **/
@Component
@EnableJms
public class ActiveMqConfig {
    @Value("${myqueue}")
    private String queueName;

    @Bean
    public Queue queue(){
        return new ActiveMQQueue(queueName);
    }

}
