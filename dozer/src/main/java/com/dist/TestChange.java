package com.dist;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-07 16:34
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class TestChange {
    static  Mapper mapper=new DozerBeanMapper();

    public static void main(String[] args) {

        A a11=new A("a",new ArrayList<>());
        A a12=new A("a",new ArrayList<>());
        A a13=new A("a",new ArrayList<>());
        A a14=new A("a",new ArrayList<>());

        A a21=new A("a",new ArrayList<>());
        A a22=new A("a",new ArrayList<>());
        A a23=new A("a",new ArrayList<>());
        A a24=new A("a",new ArrayList<>());

        A a1=new A("a",new ArrayList<A>(){{
            add(a11);add(a12);add(a13);add(a14);
        }});
        A a2=new A("a",new ArrayList<A>(){{
            add(a21);add(a22);add(a23);add(a24);
        }});
        List<A> as=new ArrayList<A>(){{
           add(a2);add(a2);
        }};


        List<B> bs=new ArrayList<B>();
        change(as,bs);
        System.out.println(bs);
    }

    /**
     * 集合类型转换
     * @param listA
     * @param listB
     */
    public static void change(List<A> listA,List<B> listB){

        //将涉及的 基本类型进行直接的转换
        listA.stream().map(a -> mapper.map(a,B.class)).collect(Collectors.toList()).forEach(b -> {
            b.getList().clear();
            listB.add(b);
        });
        for(int i=0;i<listA.size();i++){
            List listTemp=listA.get(i).getList();
            if(listTemp==null||listTemp.isEmpty()){
                continue;
            }else {
                change(listTemp,listB.get(i).getList());
            }
        }
    }

}
