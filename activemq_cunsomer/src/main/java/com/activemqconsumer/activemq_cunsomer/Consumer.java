package com.activemqconsumer.activemq_cunsomer;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-29 14:28
 * @from
 **/
@Service
public class Consumer {

    @JmsListener(destination = "${myqueue}")
    public void ConsumeMsg(TextMessage textMessage) throws JMSException {
        System.out.println("消费消息:"+textMessage.getText());
    }


}
