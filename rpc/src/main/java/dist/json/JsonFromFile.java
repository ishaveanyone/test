package dist.json;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-22 15:25
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class JsonFromFile {

    public static void main(String[] args) throws IOException {

        try {
            List<String> lines = Files.readAllLines(Paths.get("G:/SmartAnalysisIndicatorConfig.json"), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line);
            }
            String jsonStr = sb.toString();

            System.out.println(JSON.parseObject(jsonStr).toJSONString());
        }catch (IOException e){
        }
        
    }
}
