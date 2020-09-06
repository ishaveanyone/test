package com.dist.enumz;

public class Main {
    public static void main(String[] args) {

        PoolEnum.A.setObject(new Object());
        System.out.println(PoolEnum.A.name());
        System.out.println(PoolEnum.A.toString());
        System.out.println(PoolEnum.B.getObject());
        System.out.println(PoolEnum.A.getObject());

    }
}
