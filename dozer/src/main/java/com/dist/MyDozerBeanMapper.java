package com.dist;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.MappingException;
import org.dozer.MappingProcessor;
import org.dozer.stats.StatisticsInterceptor;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MyDozerBeanMapper {

    private Mapper mapper=new DozerBeanMapper();
    public void change(List<A> listA,List<B> listB){

        //将涉及的 基本类型进行直接的转换
        listB=listA.stream().map(a -> mapper.map(a,B.class)).collect(Collectors.toList());

        for(int i=0;i<listA.size();i++){
            listA.get(i).getList();
            if(listB.get(i).getList()!=null){
                listB.get(i).getList().clear();
                change(listA,listB);
            }else {
                continue;
            }
        }



    }

}
