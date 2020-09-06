/**
 * Date: 2020-08-02 14:17
 * Author: xupp
 */

package org.example;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
//关于es中index的操作 index类比关系型数据库 就是 数据库
public class EsIndexTest {
    String index="preson";
    String type="man";//类比关系型数据库 就是 表


    //测试连接
    @Test
    public void testConn(){
        RestHighLevelClient restHighLevelClient=
                EsClient.getConn();
        System.out.println(restHighLevelClient);
    }
    //测试java 创建索引
    @Test
    public void createIndex() throws IOException {
        Settings.Builder builder=
                Settings.builder().put("number_of_shards",3)
                .put("number_of_replicas",5);

        //创建mappings
        XContentBuilder xContentBuilder= JsonXContent.contentBuilder()
                .startObject()
                    .startObject("properties")
                        .startObject("name")
                            .field("type","text")
                        .endObject()
                        .startObject("age")
                            .field("type","integer")
                        .endObject()
                    .endObject()
                .endObject();
        //将mappings 和settings封装到request
        CreateIndexRequest request=new CreateIndexRequest(index)
                .settings(builder)
                .mapping(type,xContentBuilder);


        //通过client 连接
        CreateIndexResponse createIndexResponse=
                EsClient.getConn().indices().create(request, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    // 检查索引是否存在
    @Test
    public  void exist() throws IOException {
        //通过检索request
        GetIndexRequest request=new GetIndexRequest();
        request.indices(index);
        boolean flag= EsClient.getConn().indices().exists(request,RequestOptions.DEFAULT);
        System.out.println(flag);
    }
    //删除索引
    @Test
    public void deleteIndex() throws IOException {

        DeleteIndexRequest deleteRequest=new DeleteIndexRequest();
        deleteRequest.indices(index);
        EsClient.getConn().indices().delete(deleteRequest,RequestOptions.DEFAULT);
        exist();
    }
}
