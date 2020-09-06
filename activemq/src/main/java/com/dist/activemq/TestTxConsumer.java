/**
 * Date: 2020-08-06 17:27
 * Author: xupp
 */

package com.dist.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestTxConsumer {
    //测试连接mq
    public static void main(String[] args) throws JMSException {
        //建立连接工厂，单例
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://121.36.202.6:61616");
        //建立连接
        Connection connection = connectionFactory.createConnection();
        //连接开启
        connection.setClientID("cs9");
        connection.start();
        //创建一个服务对象session  消费者开启事务之后 也是需要手动提交 不然队列的消息会一直存在 会出现重复消费
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建一个目的地Destination，,即ActiveMQ的接收点
        Topic topic = session.createTopic("test-sub");
        //创建消费者
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"cs");
        Message message=topicSubscriber.receive();

        while(message!=null){

            System.out.println((((TextMessage)message).getText()));
            message=topicSubscriber.receive(1000);
        }

        topicSubscriber.close();
        session.close();
        connection.close();
    }



}
