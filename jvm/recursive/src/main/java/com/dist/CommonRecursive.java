package com.dist;

/**
 * 递归分为一般递归 和 尾递归
 * 一般递归调用 将函数作为当前调用计算结果一部分进行返回
 *
 */
public class CommonRecursive {

    public static Integer test( Integer n ){
        if(n==10)
            return n;
        return n+test(n+1);//将下一次的计算结果和n相加然后作为这一次调用的返回结果进行返回
    }


    public static void main(String[] args) {
        System.out.println( test(8));
    }
}
