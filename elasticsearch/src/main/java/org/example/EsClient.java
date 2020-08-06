/**
 * Date: 2020-08-02 11:25
 * Author: xupp
 */

package org.example;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

public class EsClient {

    public static RestHighLevelClient getConn(){
        HttpHost httpHost=new HttpHost("127.0.0.1",9200);

        RestClientBuilder restClientBuilder=RestClient.builder(httpHost);

        RestHighLevelClient restHighLevelClient=new RestHighLevelClient(restClientBuilder);

        return restHighLevelClient;
    }
}
