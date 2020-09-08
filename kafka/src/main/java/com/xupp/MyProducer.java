/**
 * Date: 2020-09-07 18:09
 * Author: xupp
 */

package com.xupp;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 *   kafka下载和安装  http://mirrors.hust.edu.cn/apache/kafka/0.9.0.0/
 *   http://czj4451.iteye.com/blog/2041096
 *   window 启动：
 bin\windows\zookeeper-server-start.bat config\zookeeper.properties
 bin\windows\kafka-server-start.bat config\server.properties
 */
public class MyProducer
{
    public static void main(String[] args) {
        Properties pros=new Properties();
        String groupID="test";
        pros.put("bootstrap.servers","127.0.0.1:9092");
        pros.put("group.id",groupID);
        pros.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        pros.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        pros.put("acks","-1");
        pros.put("retries",3);
        pros.put("batch.size",323840);
        pros.put("linger.ms",10);
        pros.put("buffer.memory",33554432);
        pros.put("max.block.ms",3000);
        Producer<String,String> producer = new KafkaProducer<String,String>(pros);
        for (int i = 0; i < 10; i++) {
            System.out.println("发送");
            Future<RecordMetadata> future= producer.send(new ProducerRecord<String, String>("test-pp", Integer.toString(i), Integer.toString(i)));

        }
        producer.close();

    }
}