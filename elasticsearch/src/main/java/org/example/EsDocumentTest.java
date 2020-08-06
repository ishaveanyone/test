/**
 * Date: 2020-08-02 14:49
 * Author: xupp
 */

package org.example;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.lucene.index.IndexReader;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//文档的操作，类比关系型数据库就是表
public class EsDocumentTest {
    String index="preson";
    String type="man";//类比关系型数据库 就是 表
    ObjectMapper mappers=new ObjectMapper();
    @Test
    public void insertDoc() throws IOException {
        //准备json的数据
        Person p=new Person();
        p.setAge(12);
        p.setId("1");
        p.setName("许平平");
        String json = mappers.writeValueAsString(p);
        //准备一个request
        IndexRequest indexRequest=
                new IndexRequest(index,type,p.getId());
        indexRequest.source(json, XContentType.JSON);
        //使用client进行添加
        IndexResponse resp=
                EsClient.getConn().index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(resp.getResult());
    }

    //使用doc 方式进行文档修改
    @Test
    public void update() throws IOException {
        Map<String,Object> map=new HashMap();
        //创建一个map
        map.put("name","ce");

        UpdateRequest request=new UpdateRequest(index,type,"1");
        //通过client 执行
        request.doc(map);
        UpdateResponse resp=
               EsClient.getConn().update(request,RequestOptions.DEFAULT);
        System.out.println(resp);
    }
    //删除文档
    @Test
    public void delete() throws IOException {
        String id="1";
        DeleteRequest deleteRequest=new DeleteRequest(index,type,id);
        DeleteResponse deleteResponse=EsClient.getConn().delete(deleteRequest,RequestOptions.DEFAULT);
        System.out.println(deleteResponse.getResult());
    }

    //批量操作文档

    //批量添加
    @Test
    public void bunchInsert() throws IOException {
        //准备多个的json数据
        Person p1=new Person("1","a",12);
        Person p2=new Person("2","b",13);
        Person p3=new Person("3","b=c",14);
        String json1=mappers.writeValueAsString(p1);
        String json2=mappers.writeValueAsString(p2);
        String json3=mappers.writeValueAsString(p3);
        //创建request
        BulkRequest request=new BulkRequest();
        request.add(new IndexRequest(index,type,p1.getId()).source(json1,XContentType.JSON));
        request.add(new IndexRequest(index,type,p2.getId()).source(json2,XContentType.JSON));
        request.add(new IndexRequest(index,type,p3.getId()).source(json3,XContentType.JSON));
        //执行
        EsClient.getConn().bulk(request,RequestOptions.DEFAULT);
    }
    //批量删除
    @Test
    public void bunckDelete() throws IOException {
        BulkRequest bulkRequest=new BulkRequest();
        bulkRequest.add(new DeleteRequest(index,type,"1"));
        bulkRequest.add(new DeleteRequest(index,type,"2"));
        bulkRequest.add(new DeleteRequest(index,type,"3"));
        EsClient.getConn().bulk(bulkRequest,RequestOptions.DEFAULT);
    }



}
