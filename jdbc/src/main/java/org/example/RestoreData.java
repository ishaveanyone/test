/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-03-31 14:36
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.sql.*;
import java.util.UUID;

//做下数据转存
public class RestoreData {


    private static String purl = "jdbc:postgresql://172.27.27.249:15432/dgpers_zj";
    private static String puser = "dgpers_zj";
    private static String ppassword = "Passw0rd";

    private static Connection pconn;

    private static String result = "";



    private static JSONArray qy_tdlyjgtz_json;


    public static void before() {
        connect();
        String tdlyjgtzJson;
        try {
            File file = new File("D:/tdlyjgtz_history_init.json");
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            tdlyjgtzJson = FileCopyUtils.copyToString(br);
            qy_tdlyjgtz_json = JSON.parseArray(tdlyjgtzJson);
        } catch (IOException e) {
            qy_tdlyjgtz_json = null;
        }


    }










    private static void connect() {
        // 初始化驱动包
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("开始尝试连接postgresql数据库！");
            pconn = DriverManager.getConnection(purl, puser, ppassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }



    //初始化导入的数据的工程目标数据以及 土地利用数据 还有 初始化 每一个导入工程的 阶段

    public static void  main(String[] args) {
        before();
        System.out.println("如果是添加全域的工程基本信息那么久进行数据的初始化");

        System.out.println("数据"+qy_tdlyjgtz_json.toJSONString());
        //保存所有的工程目标
        try {
            String sql_0_select = "select * from prj_qytdzz_gcjbxx";
            //
            Statement sm = pconn.createStatement();
            ResultSet rs0 = sm.executeQuery(sql_0_select);
            while (rs0.next()){
                //从 工程目标表中查找是否存在这个数据 如果存在那么久进行数据的添加
                String guid=rs0.getString("xmbh");
                //查看是不是已经存在合计了 如果 不存在 这个那么久进行每一个工程的添加操作
                String sql_1_select="select * from prj_qytdzz_tdlyjgtzqk where gcbh=? and ydmc='合计'";
                PreparedStatement ps0 = pconn.prepareStatement(sql_1_select);
                ps0.setString(1, guid);
                //
                ResultSet rs1=  ps0.executeQuery();
                //如果数据不存在那么 就进行数据的填充
                if(!rs1.next()){
                    for (Object o : qy_tdlyjgtz_json) {
                        String sql_1_insert="insert into prj_qytdzz_tdlyjgtzqk(xmbh,create_time,gcbh,ydlx,ydmc)" +
                                " values(?,?,?,?,?)";
                        PreparedStatement ps2 = pconn.prepareStatement(sql_1_insert);
                        ps2.setString(1,UUID.randomUUID().toString());
                        ps2.setDate(2,new Date(System.currentTimeMillis()));
                        ps2.setString(3,guid);
                        ps2.setString(4,((JSONObject)o).getString("ydlx"));
                        ps2.setString(5,((JSONObject)o).getString("ydmc"));
                        ps2.executeUpdate();
                        ps2.close();
                    }
                }
                rs1.close();
                ps0.close();
                System.out.println("结束工程："+guid+"导入工作");
            }
            rs0.close();
            sm.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
