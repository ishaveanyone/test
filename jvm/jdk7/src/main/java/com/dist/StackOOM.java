package com.dist;

/**
 * 方法栈上面会出现 oom 当栈上内存不够 通过xss设置
 * 如果线程请求的栈的深度大于虚拟机允许的深度 会出现StackOverflow
 */
public class StackOOM {
    int a=1;
    public void deep(){
        a++;
        deep();
    }
    public static void main(String[] args) {
        StackOOM stackOOM=new StackOOM();
        try{
            stackOOM.deep();
        }catch (Exception e){
            System.out.println(stackOOM.a);
            System.out.println(e.getCause());
        }
    }
}
