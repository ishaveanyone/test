/**
 * Date: 2020-08-06 17:27
 * Author: xupp
 */

package com.dist.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class TestTopicConsumer {
    //测试连接mq
    public static void main(String[] args) throws JMSException {
        //建立连接工厂，单例
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://121.36.202.6:61616");
        //建立连接
        Connection connection = connectionFactory.createConnection();
        //连接开启
        connection.start();
        //创建一个服务对象session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目的地Destination，,即ActiveMQ的接收点
        Topic topic = session.createTopic("test-topic");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        /**
         * 使用循环遍历的方式读取消息
         */
//        while (true) {
//            TextMessage message=(TextMessage) consumer.receive();
//            System.out.println(message.getText());
//        }

        /**
         * 使用监听的方式进行消息读取
         */

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    try {
                        System.out.println(((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        try {
            System.in.read(); // 加上阻塞保证能够读到消息
        } catch (IOException e) {
            e.printStackTrace();
        }
        consumer.close();
        session.close();
        connection.close();
    }



}
