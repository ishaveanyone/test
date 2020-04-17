/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-04-11 9:40
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package org.example;

import com.alibaba.fastjson.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Main {


    private static String ourl = "jdbc:oracle:thin:@192.168.1.94:1521:orcl";
    private static String ouser = "dist_test";
    private static String opassword = "pass";

    private static String purl = "jdbc:postgresql://192.168.200.123:15432/dgpers_dev";
    private static String puser = "dgpers_dev";
    private static String ppassword = "Passw0rd";

    private static Connection oconn;
    private  static Connection pconn;

    static  String  result = "";
    public void connect(){
        // 初始化驱动包
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            // 根据数据库连接字符，名称，密码给conn
            System.out.println("开始尝试连接oracle数据库！");
            oconn= DriverManager.getConnection(ourl, ouser, opassword);
            Class.forName("org.postgresql.Driver");
            System.out.println("开始尝试连接postgresql数据库！");
            pconn= DriverManager.getConnection(purl, puser, ppassword);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        try {
            InputStream mAssets = new FileInputStream("H:/region.json");
            int lenght = mAssets.available();
            byte[] buffer = new byte[lenght];
            mAssets.read(buffer);
            mAssets.close();
            result = new String(buffer, StandardCharsets.UTF_8); // 关键之处

        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    public static void main(String[] args) {
        JSONObject regionObject=JSONObject.parseObject(result);

        String osql = "select * from prj_qytdzz_gcjbxx";
        try {
            Statement sm = pconn.createStatement();
            ResultSet  rs = sm.executeQuery(osql);
            while(rs.next()) {
                String szs=rs.getString("szs");
                if(szs!=null){
                    szs+="00";
                    szs=(String) regionObject.get(szs);
                }
                String szx=rs.getString("szx");
                if(szx!=null){
                    szx=(String) regionObject.get(szs);
                }
                String sql = "UPDATE prj_qytdzz_gcjbxx SET szs=?, szx=? WHERE xmbh=?";
                PreparedStatement ps = pconn.prepareStatement(sql);
                ps.setString(1, szs);
                ps.setString(2, szx);
                ps.setString(3, rs.getString("xmbh"));
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
