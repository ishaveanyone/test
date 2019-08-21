package com.dist.jmx;


import java.util.List;

public interface HelloMBean
{
    public Integer getA();

    public void setA(Integer a);
    public List<String> getData();
    public void setData(List<String> data);


    public String getName();

    public void setName(String name);

    public String getAge();

    public  void outprint();

    public void setAge(String age);

    public void helloWorld();

    public void helloWorld(String str);

    public void getTelephone();

    public void add(String ele);

    public void out();
}