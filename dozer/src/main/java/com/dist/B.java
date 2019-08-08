package com.dist;

import java.util.List;

/**
 *：
 */
public class B {

    private String name;

    private List<B> list;

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

    public B(){

    }

    public B(String name,List<B> list){
        this.name=name;
        this.list=list;
    }
}
