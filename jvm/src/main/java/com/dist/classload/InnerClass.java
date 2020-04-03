/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-03-31 10:09
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist.classload;

/**
 * 嵌套类
 */
public class InnerClass {
    private String name;
    private String name2;
    private static String name4;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public  void out(){
        //内部类只能

        InnerClassA innerClassA=new InnerClassA();
        System.out.println(innerClassA.name3);
    }

    static class InnerClassA {
        private String name;
        private String name3;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void out() {
            //可以直接访问外部类成员 静态内部类 不能直接访问外部内部类成员
            System.out.println(name4);
        }
    }

    public static void main(String[] args) {
    }
}
