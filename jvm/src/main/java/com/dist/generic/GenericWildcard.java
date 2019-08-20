package com.dist.generic;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-20 14:49
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc： 泛型通配符 ？ 这个是一种实际的类型 表示的是 不确定类型的类型
 */
public class GenericWildcard<T> {
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}


class Main{
    //当不确定使用的 泛型具体的类型的时候 应该使用 通配符 通配符可以配合 上下边界进行使用 extends表示XXXX 子类 super 表示 是 XXXX 父类
   public static void user(GenericWildcard<? extends Object> tGenericWildcard){
       System.out.println(tGenericWildcard.getT());
   }

    public static void main(String[] args) {
        GenericWildcard<String> stringGenericWildcard=new GenericWildcard<>();
        stringGenericWildcard.setT("aa");
        user(stringGenericWildcard);

        GenericWildcard<Integer> integerGenericWildcard=new GenericWildcard<>();
        integerGenericWildcard.setT(1);
        user(integerGenericWildcard);
    }
}