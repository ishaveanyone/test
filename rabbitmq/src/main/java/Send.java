import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-02-12 17:39
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

public class Send {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception {
//      ...

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

        }
    }

}
