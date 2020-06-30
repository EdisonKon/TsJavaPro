package TsActiveMQ.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-29 09:26
 * @from
 **/
public class Producter {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic hello = session.createTopic("hello");
        MessageProducer producer = session.createProducer(hello);
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("ActiveMQ啦啦啦：这是第 " + i + " 条消息");
            producer.send(message);
        }
//        session.commit(); 事务开启才需要
        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送完毕");

    }

}
