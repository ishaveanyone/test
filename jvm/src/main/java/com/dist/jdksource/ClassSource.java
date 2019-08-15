package com.dist.jdksource;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-14 16:35
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

interface P{

}
class B implements P{

}


class A implements P{


    public A(String name) {
        this.name = name;
    }

    public A() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
public class ClassSource {


    public static void main(String[] args) throws Exception {

        A a=new A("测试");
        System.out.println(a.getClass().toString());
        Class clazz=Class.forName("com.dist.jdksource.A");
        System.out.println(clazz.getName());
        //这个方法主要是用于 对没有加载的类 进行加载的 所以应该是 从网络上download 本地类 还没有加载进内存 进行的操作
        Class.forName("com.dist.jdksource.A",true,ClassSource.class.getClassLoader());
        A a1=(A) clazz.newInstance();
        System.out.println(a1.getName());
        Class class2=a.getClass();//同一份字节码
        System.out.println(class2.isInstance(a1));

        //是否是同一份字节码，参数 class 是这个类的 子类
        System.out.println(P.class.isAssignableFrom(clazz));

        //判断是否是一个接口
        System.out.println(P.class.isInterface());

        A[] as=new A[1];
        System.out.println(as.getClass().isArray());

        System.out.println(Integer.valueOf(1).getClass().isPrimitive());

    }


}
