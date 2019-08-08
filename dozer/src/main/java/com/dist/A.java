package com.dist;

import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-07 16:10
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class A {

    private String name;

    private List<A> list;




    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public A(){

    }

    public A(String name,List<A> list){
        this.name=name;
        this.list=list;
    }
}
