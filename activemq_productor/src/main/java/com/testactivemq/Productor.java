package com.testactivemq;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.*;

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
        jmsMessagingTemplate.convertAndSend(queue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage("6666");
                textMessage.setStringProperty("JMSXGroupID","666");
                return null;
            }
        });
    }

    @Scheduled(fixedDelay = 3000)
    public void scheduleSendMsg(){

    }

}
