package com.dist.abstractclass;

import java.util.Calendar;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-21 16:22
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public abstract class Test {

}



class Main{
    public static void main(String[] args) {

    }
}

interface A{

}

interface B{

}

interface C extends A,B{

}


abstract class D{
    D(){
        System.out.println(11);
    }
}

class E extends D{
    //类初始化的时候
    E(){
//        super()
    }
    public static void main(String[] args) {

        new E();
        new E();
    }
}