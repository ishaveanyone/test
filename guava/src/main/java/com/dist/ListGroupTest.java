package com.dist;

import com.google.common.base.Function;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;


class Goods {
    private Integer id;
    private Integer groupId;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "{id="+id + ", groupId=" + groupId + ", name=" + name + "}";
    }
}
//测试集合分组功能
public class ListGroupTest
{
    public static void main(String[] args) {
        List<Goods> list = new ArrayList<Goods>();
        {
            Goods e = new Goods();
            e.setId(1);
            e.setGroupId(1);
            e.setName("商品1");
            list.add(e);
        }

        {
            Goods e = new Goods();
            e.setId(3);
            e.setGroupId(3);
            e.setName("商品3");
            list.add(e);
        }
        {
            Goods e = new Goods();
            e.setId(4);
            e.setGroupId(2);
            e.setName("商品4");
            list.add(e);
        }
        {
            Goods e = new Goods();
            e.setId(5);
            e.setName("商品5");
            list.add(e);
        }
        {
            Goods e = new Goods();
            e.setId(2);
            e.setGroupId(2);
            e.setName("商品2");
            list.add(e);
        }
        //根据groupId分组, 如果groupId为null, 则放到默认为0的group下
        Function<Goods, Integer> fun = new Function<Goods, Integer>() {
            @Override
            public Integer apply(Goods input) {
                if (input.getGroupId() == null) {
                    return 0;
                }
                return input.getGroupId();
            }
        };
        Multimap<Integer, Goods> index = Multimaps.index(list, fun);
        Map<Integer, Collection<Goods>> map = index.asMap();
        for (Map.Entry<Integer, Collection<Goods>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " <---> " + entry.getValue());

        }
    }
}
