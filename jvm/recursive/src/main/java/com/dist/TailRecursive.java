package com.dist;

/**
 * 尾递归是 将下一次的返回值作为当前的结果直接进行返回
 */
public class TailRecursive {

    public static Integer test( Integer n1 ,Integer result){
        result+=n1;
        if(n1==0)
            return result;
        return test(n1-1,result);//将下一次的计算结果直接进行返回不用保存局部变量 减少jvm栈的开销

    }

    public static void main(String[] args) {
        System.out.println(test(3,0));
    }
}
