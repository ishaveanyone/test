package com.dist;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-22 13:18
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：配置使用 jconsole 进行数据监控
 */
@Component
@ManagedResource(objectName = "monitor:name=IndicatorConfig")
public class IndicatorConfigMBean {

    private static Logger logger= LoggerFactory.getLogger(IndicatorConfigMBean.class);

    private  static LinkedHashMap<String,List<String>> application=new LinkedHashMap();

    private  static LinkedHashMap<String,List<String>> level=new LinkedHashMap();



    //使用非静态块 因为只有 重启重新加载的时候才会 进行数据 的 重新刷新 否则 不会刷新
   static  {
        try {

            URI uri = ClassLoader.getSystemResource("config").toURI();
            String mainPath = Paths.get(uri).toString();
            List<String> lines = Files.readAllLines(Paths.get(mainPath ,"SmartAnalysisIndicatorConfig.json"), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            for (String line : lines) {
                sb.append(line);
                System.out.println(line);
            }
            String jsonStr = sb.toString();
            flush(jsonStr);
        }catch (IOException e){
            logger.error("初始化指标行业应用以及指标等级失败：{}",e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //添加一个
    @ManagedOperation(description = "刷新数据,顺便会将新的数据写到文件里面")
    public static void flush(String jsonValue){
        //清空数据
        application.clear();
        level.clear();
        JSONObject data=JSON.parseObject(jsonValue);
        JSONObject applicationJson=data.getJSONObject(Constants.IndicatorApplicationAndLevel.APPLICATION_KEY);
        applicationJson.keySet().forEach(s -> {
            ArrayList<String> values=new ArrayList();
            applicationJson.getJSONArray(s).forEach(o -> {
                values.add(o.toString());
            });
            application.put(s,values);
        });

        int beginIndex=Constants.IndicatorApplicationAndLevel.LEVEL_SUFFIX_INDEX;
        while (true){
            //不存在就退出
            if(!data.containsKey(Constants.IndicatorApplicationAndLevel.LEVEL_PREFIX+beginIndex)){
                break;
            }
            JSONArray levelJsonArray =data.getJSONArray(
                    Constants.IndicatorApplicationAndLevel.LEVEL_PREFIX+beginIndex
                    );
            List<String> levelValueList=new ArrayList<>();
            levelJsonArray.forEach(o -> {
                levelValueList.add(o.toString());
            });
            level.put(Constants.IndicatorApplicationAndLevel.LEVEL_PREFIX+beginIndex,
                    levelValueList);
            beginIndex++;
        }
        //将新的json写到文件中
        try {
            URI uri = ClassLoader.getSystemResource("config").toURI();
            String mainPath = Paths.get(uri).toString();
            Files.write(Paths.get(mainPath ,"SmartAnalysisIndicatorConfig.json"),jsonValue.getBytes(StandardCharsets.UTF_8));
        }catch (IOException e){
            logger.error("刷新指标行业应用以及指标等级失败：{}",e.getMessage());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    @ManagedOperation(description = "打印数据查看是否是对的")
    public static void logPrint(){
        logger.info("行业应用数据：{}",JSON.toJSONString(application));
        logger.info("指标等级数据：{}", JSON.toJSONString(level));
    }

}
