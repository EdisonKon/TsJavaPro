package TsActiveMQ.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author dekai.kong
 * @difficult
 * @create 2020-06-29 09:26
 * @from
 **/
public class Customer {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic hello = session.createTopic("hello");
        MessageConsumer consumer = session.createConsumer(hello);
        //第一种方式,receive
//        while (true) {
//            TextMessage message = (TextMessage) consumer.receive();
//            if (message != null) {
//                System.out.println("接收到消息： " + message.getText());
//            } else {
//                break;
//            }
//        }
        //第二种方式,MessageListener
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage) message;
                try {
                    System.out.println("listener接收到消息： " + tm.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer.close();
        session.close();
        connection.close();
    }

}
