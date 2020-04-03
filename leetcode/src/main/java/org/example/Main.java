/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-03-19 11:18
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package org.example;

public class Main {

    public static void main(String[] args) {
        reverse(-2147483648);
    }
    //获取最长无重复字符字串
    public int lengthOfLongestSubstring(String s) {
        //使用暴力的方法只需要进行一般长度的字符进行遍历
        int max=0;

        return 1;
    }

    public static  int reverse(int x) {
        Long tx=0L;
        if(x<0){
            tx=Long.valueOf(0-x);
        }
        String s=String.valueOf(tx);
        StringBuffer sb=new StringBuffer();
        int index=s.length()-1;
        boolean flag=false;
        while(index>=0){
            if(s.indexOf(index)=='0'){
                if(!flag){
                    continue;
                }
            }
            flag=true;
            sb.append(s.charAt(index));
            index--;
        }
       Long cint= Long.valueOf(sb.toString());
        if(x<0){
            cint=0-cint;
        }
//−231,  231 − 1
        if(cint<-Math.pow(2,31)||cint>=Math.pow(2,31))
            return 0;
        return cint.intValue();



    }


}
