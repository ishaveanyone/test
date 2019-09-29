package com.dist;

import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

//import com.mongodb.MongoClient;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-19 17:31
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 * 各种连接mongo的方式
 */
public class ConnectMongo {

    static MongoClient way1(){
        return MongoClients.create();
    }
    static MongoClient way2(){
        return  MongoClients.create("mongodb://localhost:27017");
    }
    //连接多个
    static MongoClient way3(){
      return  MongoClients.create("mongodb://localhost:27017,192.168.1.95:27017");
    }
    static com.mongodb.MongoClient way4(){
        return new com.mongodb.MongoClient( "localhost" , 27017 );
    }

    static com.mongodb.MongoClient way5() {
        return new com.mongodb.MongoClient(new MongoClientURI("mongodb://localhost:27017"));
    }


    //指定ssl 进行连接

    static MongoClient way6() {
        return MongoClients.create("mongodb://localhost/?ssl=true");
    }

    public static void main(String[] args) {
//      数据库连接池
        MongoClient mongoClient1= way1();
        System.out.println(mongoClient1.getDatabase("test").getName());
        MongoClient mongoClient2= way2();
        System.out.println(mongoClient2.getDatabase("test").getName());

        MongoClient mongoClient3= way3();

        System.out.println(mongoClient3.getDatabase("test").getName());

        System.out.println(way4().getDatabase("test").getName());

        System.out.println(way5().getDatabase("test").getName());

    }
}
