package com.dist.generic;

import sun.rmi.runtime.Log;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-20 11:20
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 *
 * 泛型类型用于类的定义中，被称为泛型类。
 * 通过泛型可以完成对一组类的操作对外开放相同的接口。
 * 最典型的就是各种容器类，如：List、Set、Map。
 *
 *
 *
 */
public class GenericClass <T> {
    //key这个成员变量的类型为T,T的类型由外部指定
    private T key;

    public GenericClass(T key) { //泛型构造方法形参key的类型也为T，T的类型由外部指定
        this.key = key;
    }

    public T getKey(){ //泛型方法getKey的返回值类型为T，T的类型由外部指定
        return key;
    }

}

class App{
    public static void main(String[] args) {
        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
//传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        GenericClass<Integer> genericInteger = new GenericClass<Integer>(123456);

//传入的实参类型需与泛型的类型参数类型相同，即为String.
        GenericClass<String> genericString = new GenericClass<String>("key_vlaue");
        System.out.println("泛型测试 key is " + genericInteger.getKey());
        System.out.println("泛型测试 key is " + genericInteger.getKey());
    }
}
