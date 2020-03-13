import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.Map;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-12-25 16:15
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

public class JongoTest {
    public static void main(String[] args) {
        ServerAddress serverAddress = new ServerAddress("192.168.200.232",27017);

        MongoClient mongoClient= new MongoClient(serverAddress);
        DB db =mongoClient.getDB("ims");

        Jongo jongo = new Jongo(db);
        MongoCollection collection = jongo.getCollection("dds_system-catalog");

        Map all = collection.findOne("{_id: '500519'}").as(Map.class);
        System.out.println(all.get("systemType"));


    }

}
