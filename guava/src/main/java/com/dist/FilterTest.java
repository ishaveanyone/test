package com.dist;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class AB{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class FilterTest {

    public static void main(String[] args) {

        AB ab1=new AB();
        AB ab2=new AB();
        ab1.setName(null);
        ab2.setName("1111");

        List<AB> abs=new ArrayList();
        abs.add(ab1);
        abs.add(ab2);
         Collection<AB> flist= Collections2.filter(abs,new Predicate<AB>(){
            @Override
            public boolean apply(AB projectListEntity) {
                return projectListEntity.getName()!=null;
            }
        });
        abs  =new ArrayList<>(flist);
        System.out.println(1);
    }
}
