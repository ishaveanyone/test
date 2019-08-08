package com.dist;

import java.util.List;


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
