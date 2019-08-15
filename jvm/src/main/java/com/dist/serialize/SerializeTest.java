package com.dist.serialize;

import java.io.*;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 15:49
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class SerializeTest {

    public static void main(String[] args) throws Exception {
        A a=new A();
        a.setId(1);
        a.setName("测试");
        toFile(a);
        A a1=(A)fromFile();
        System.out.println(a1.getId());





    }

    static void toFile(Object o) throws Exception{
        FileOutputStream fileOutputStream=new FileOutputStream(new File("G:/成果数据基本信息.ini"));
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(o);
    }

    static Object fromFile() throws Exception{
        FileInputStream fileInputStream=new FileInputStream(new File("G:/成果数据基本信息.ini"));
        ObjectInputStream objectOutputStream=new ObjectInputStream(fileInputStream);
        return objectOutputStream.readObject();

    }


}
