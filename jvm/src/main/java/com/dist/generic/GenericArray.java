package com.dist.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-20 15:22
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：泛型数组
 */
class Model<T>{

}
public class GenericArray {

    public static void main(String[] args) {
//        List<String>[] ls = new ArrayList<String>[10]; undo
        List<?>[] ls = new ArrayList<?>[10];
        List<String>[] ls2 = new ArrayList[10];
        Model<String>[] model=new Model[10];
    }

}
