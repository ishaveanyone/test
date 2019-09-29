package com.dist;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

//创建 链接测试
public class TestConntect {

    /**
     * 使用MongoClients.create()，
     * 或MongoClient(对旧式MongoClient) API进行连接，
     * 以连接到正在运行的MongoDB实例。
     */
    public static void testNewApiConnect(){
        MongoClient mongoClient = MongoClients.create();
        System.out.println(mongoClient.getDatabase("test").getCollection("test").find());
    }
    public static void testOldApiConnect(){
      //直接 通过 构造喊声1 进行链接的建立
        com.mongodb.MongoClient mongoClient=new com.mongodb.MongoClient();
        System.out.println(mongoClient.getDatabase("test").getCollection("test").find());
    }

    public static void main(String[] args) {
        testNewApiConnect();
        testOldApiConnect();
    }

}
