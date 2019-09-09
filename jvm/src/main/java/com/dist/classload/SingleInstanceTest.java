package com.dist.classload;

import sun.security.jca.GetInstance;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-02 15:28
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：本代码主要是为了引申使用 单元数枚举类型
 *  创建单例的方式
 */
class O{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class A{
    public final static O o=new O();//公共静态域
}

class B{
    private  final static O o=new O();//私有静态域
    O getInstance(){
        return o;
    }
}

//以上两种 容易被 反序列化修改 ，而且 进行序列化之后 反序列化 的对象是新的对象,当然上面可以使用 饿汉懒汉的模式进行修饰编码

//使用单元素的 枚举类型 静态类
enum OSin{
    INSTANCE;
   public void say(){
       System.out.println("1111");
   }
}
public class SingleInstanceTest {

    public static void main(String[] args) {
        OSin.INSTANCE.say();


    }
}
