package com.dist.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TestTopicProvider {

    //测试连接mq
    public static void main(String[] args) throws JMSException {
        //建立连接工厂，单例
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://121.36.202.6:61616");
        //建立连接
        Connection connection = connectionFactory.createConnection();
        //连接开启
        connection.start();
        //创建一个服务对象session
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建一个目的地Destination，,即ActiveMQ的接收点
        Topic topic = session.createTopic("test-topic");
        //创建一个生产者，并将目的地告诉他
        MessageProducer producer = session.createProducer(topic);
        //创建一个消息

        for(int i=0;i<3;i++){
            Message message = session.createTextMessage("hello queue message"+i);
            //生产者发送消息
            message.setIntProperty("ce",1);//设置消息属性
            producer.send(message);
        }

        //关闭连接
        producer.close();
        session.close();
        connection.close();
    }


}
