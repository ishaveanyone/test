/**
 * Date: 2020-08-02 15:38
 * Author: xupp
 */

package org.example;

import jdk.internal.org.objectweb.asm.tree.IincInsnNode;
import org.apache.http.client.protocol.RequestDefaultHeaders;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.json.JsonXContent;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;

import java.io.IOException;

public class PreIndex {
    //先定义索引名和类型名
    String index = "sms_logs_index";
    String type = "sms_logs_type";

    @Test

    public void create_index() throws IOException {
        Settings.Builder settings = Settings.builder()
                .put("number_of_shards", 3)
                .put("number_of_replicas", 1);

        XContentBuilder mappings = JsonXContent.contentBuilder()
                .startObject()
                .startObject("properties")
                .startObject("createDate")
                .field("type", "text")
                .endObject()
                .startObject("sendDate")
                .field("type", "date")
                .field("format", "yyyy-MM-dd")
                .endObject()
                .startObject("longCode")
                .field("type", "text")
                .endObject()
                .startObject("mobile")
                .field("type", "text")
                .endObject()
                .startObject("corpName")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("smsContent")
                .field("type", "text")
                .field("analyzer", "ik_max_word")
                .endObject()
                .startObject("state")
                .field("type", "integer")
                .endObject()
                .startObject("operatorid")
                .field("type", "integer")
                .endObject()
                .startObject("province")
                .field("type", "text")
                .endObject()
                .startObject("ipAddr")
                .field("type", "text")
                .endObject()
                .startObject("replyTotal")
                .field("type", "integer")
                .endObject()
                .startObject("fee")
                .field("type", "integer")
                .endObject()
                .endObject()
                .endObject();

        CreateIndexRequest request = new CreateIndexRequest(index)
                .settings(settings)
                .mapping(type, mappings);

        RestHighLevelClient client = EsClient.getConn();
        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());

    }

    //使用term 方式进行查询 不分词是值 不会对条件字符串进行分词
    @Test
    public void termQuery() throws IOException {
        //创建request对象
        SearchRequest request = new SearchRequest();
        request.indices(index);
        request.types(type);
        //指定查询条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(5);
        searchSourceBuilder.query(QueryBuilders.termQuery("province", "湖"));
        //执行
        request.source(searchSourceBuilder);
        SearchResponse searchResponse =
                EsClient.getConn().
                        search(request, RequestOptions.DEFAULT);
        SearchHit[] searchHits = searchResponse.getHits().getHits();
        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit);
        }
    }
    @Test
    public void termsQuery() throws IOException {
        //创建request
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.types(type);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termsQuery("province", "湖", "yw"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse =
                EsClient.getConn().search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] searchHits =
                searchResponse.getHits().getHits();

        for (SearchHit searchHit : searchHits) {
            System.out.println(searchHit);
        }
    }


    /**
     * match 高层查询
     * 更具查询类型不同 那么查询的方式不一样 自动匹配类型
     * 如果需要分词久分词查询
     * 如果不需要分词就不会分词查找
     * 默认只查10条数据
     */
    @Test
    public void matchAll() throws IOException {
        //创建request
        SearchRequest request=new SearchRequest();
        request.indices(index);
        request.types(type);
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        request.source(searchSourceBuilder);

        SearchResponse response=
                 EsClient.getConn().search(request,RequestOptions.DEFAULT);
        SearchHit[]  searchHits=
                response.getHits().getHits();
        for(SearchHit searchHit:searchHits){
            System.out.println(searchHit);
        }
    }

    @Test
    public void match() throws IOException {
        //创建request
        SearchRequest request=new SearchRequest();
        request.indices(index);
        request.types(type);
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("corpName","男人养车"));
        request.source(searchSourceBuilder);

        SearchResponse response=
                EsClient.getConn().search(request,RequestOptions.DEFAULT);
        SearchHit[]  searchHits=
                response.getHits().getHits();
        for(SearchHit searchHit:searchHits){
            System.out.println(searchHit);
        }
    }





    @Test
    public void boolmatch() throws IOException {
        //创建request
        SearchRequest request=new SearchRequest();
        request.indices(index);
        request.types(type);
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("smsContext","养车 购买").operator(Operator.AND));
        request.source(searchSourceBuilder);

        SearchResponse response=
                EsClient.getConn().search(request,RequestOptions.DEFAULT);
        SearchHit[]  searchHits=
                response.getHits().getHits();
        for(SearchHit searchHit:searchHits){
            System.out.println(searchHit);
        }
    }


    @Test
    public void mutimatch() throws IOException {
        //创建request
        SearchRequest request=new SearchRequest();
        request.indices(index);
        request.types(type);
        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("车","corpName","smsContext").operator(Operator.AND));
        request.source(searchSourceBuilder);

        SearchResponse response=
                EsClient.getConn().search(request,RequestOptions.DEFAULT);
        SearchHit[]  searchHits=
                response.getHits().getHits();
        for(SearchHit searchHit:searchHits){
            System.out.println(searchHit);
        }
    }


    /**
     * id查询
     */
    @Test
    public void searchById() throws IOException {
        //创建request
        GetRequest searchRequest=new GetRequest(index,type,"6");
        GetResponse  response=
                EsClient.getConn().get(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSource());
    }
    // 通过 ids 查询  类似 sql中的 in查询
    @Test
    public void searchByIds() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds("1","2"));

        searchRequest.source(searchSourceBuilder);
       SearchResponse response=
               EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }



    //前缀查询

    @Test
    public void searchPrefix() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.prefixQuery("corpName","安居"));

        searchRequest.source(searchSourceBuilder);
        SearchResponse response=
                EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }

    //模糊查询 可以允许错别字 es 会自动识别 不稳定
    @Test
    public void searchFuzzy() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.fuzzyQuery("corpName","烂人之家"));

        searchRequest.source(searchSourceBuilder);
        SearchResponse response=
                EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }

    //sql 中的 like 可以使用 * ? 占位

    @Test
    public void searchWildcard() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wildcardQuery("corpName","男人*"));

        searchRequest.source(searchSourceBuilder);
        SearchResponse response=
                EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }

    @Test
    public void searchRange() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.rangeQuery("fee").lt(20).gt(1));

        searchRequest.source(searchSourceBuilder);
        SearchResponse response=
                EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }


    //使用正则 查询
    @Test
    public void searchRegxp() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.regexpQuery("longCode","106[0-9](8)"));

        searchRequest.source(searchSourceBuilder);
        SearchResponse response=
                EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }


    // 使用scroll 进行分页查询
    //使用正则 查询
    @Test
    public void searchScroll() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.regexpQuery("longCode","106[0-9](8)"));

        searchRequest.source(searchSourceBuilder);
        SearchResponse response=
                EsClient.getConn().search(searchRequest,RequestOptions.DEFAULT);
        for(SearchHit searchHit:response.getHits().getHits()){
            System.out.println(searchHit);
        }
    }



    // 使用bool should must_not must进行分页查询
    //使用正则 查询
    @Test
    public void searchBool() throws IOException {
        //创建request
        SearchRequest  searchRequest=new SearchRequest(index);
        searchRequest.types(type);

        SearchSourceBuilder searchSourceBuilder=new SearchSourceBuilder();
    }
}