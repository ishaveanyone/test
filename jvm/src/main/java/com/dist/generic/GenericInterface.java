package com.dist.generic;

import java.util.Random;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-20 14:33
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：泛型接口
 * 如果不声明泛型，如：class FruitGenerator implements Generator<T>，
 * 编译器会报错："Unknown class"
 */
public interface GenericInterface<T> {
    public T next();
    public void add(T t);
}

class FruitGenerator<T> implements GenericInterface<T>{
    Object arr[]=new Object[1024];
    int  index=0;
    int  nextIndex=0;

    @Override
    public T next() {

        return null;

    }

    @Override
    public void add(T t) {
        arr[index]=t;
        index++;
    }

    public static void main(String[] args) {
        new FruitGenerator<String>().next();
    }

}

/**
 * 如果 已经制定泛型类型 一定要在父类上面制定
 */
class FruitGenerator2 implements GenericInterface<String> {

    private String[] fruits = new String[]{"Apple", "Banana", "Pear"};

    @Override
    public String next() {
        Random rand = new Random();
        return fruits[rand.nextInt(3)];
    }

    @Override
    public void add(String s) {

    }
}