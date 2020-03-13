import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-12-25 15:40
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

public class MongoQuery {
    public static void main(String[] args) {
        ServerAddress serverAddress = new ServerAddress("192.168.200.232",27017);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);


        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(addrs);

        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("ims");
        MongoCollection<Document> collection  = mongoDatabase.getCollection("dds_system-catalog");

        //查询过程
        BasicDBObject query = new BasicDBObject();
        query.put("_id","500519");

        //查询结果
        //MongoCursor<Document> cursor = collection.find(query).skip(0).limit(10).iterator();
        FindIterable<Document> datas = collection.find(query);
        datas.forEach((Block<? super Map>) o->{
            System.out.println(o.get("_id"));
            List<String> indirs=(List<String>) o.get("indicators");
            for (String indir : indirs) {
                System.out.println(indir);
            }
        });
        System.out.println(1);
    }

}
