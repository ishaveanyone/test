package com.dist.generic;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-20 14:57
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc： 泛型方法
 *
 * 定义泛型方法:将泛型参数列表用尖括号括起来,放在返回值之前.
 *
 * <T>这个申明表示这个方法是一个泛型方法
 */

class F<T> {
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
public class GenericMethod{
    //泛型方法不要求外部指定 泛型  由方法自己指定泛型 但是外部是可以指定泛型的
    //但是 使用泛型T，注意这个T是一种全新的类型，可以与泛型类中声明的T不是同一种类型。如果类上也定义了泛型 T 那么 这个可以和类定义的相同 也可以不同
    public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
            IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }

    public <T> void test(){

    }

    /**
     * 事实上如果这里不使用 泛型 方法 也可以使用 通配符进行实现
     * @param t
     * @param <T>
     */
    public <T> void f(F<T> t){
        System.out.println(t.getClass().getName());
        System.out.println(t.getT());
    }

    public static void main(String[] args) {
        GenericMethod method = new GenericMethod();
        F f=new F<String>();
        f.setT("aaa");
        method.f(f);
    }
}


class StaticGenerator {

    /**
     * 如果在类中定义使用泛型的静态方法，需要添加额外的泛型声明（将这个方法定义成泛型方法）
     * 即使静态方法要使用泛型类中已经声明过的泛型也不可以。
     * 如：public static void show(T t){..},此时编译器会提示错误信息：
     "StaticGenerator cannot be refrenced from static context"

     也就是 如果静态方法要使用 泛型 那么 他一定是 一个泛型方法
     */
    public static <T> void show(T t){
        System.out.println(t.getClass().getName());
    }


}