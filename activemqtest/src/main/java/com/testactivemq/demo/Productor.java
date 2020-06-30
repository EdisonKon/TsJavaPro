package com.testactivemq.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-29 14:28
 * @from
 **/
@Service
public class Productor {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void productMsg(){
        jmsMessagingTemplate.convertAndSend(queue,"springboot-activemq-msg");
    }

    @Scheduled(fixedDelay = 3000)
    public void scheduleSendMsg(){

    }

}
