package com.dist.date.after8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-08-28 10:26
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */
public class FUN
{
    public static void main(String[] args)
            throws ParseException
    {
        Scanner scanner = new Scanner(System.in);
        String line = null;
        while (!"q".equals(line = scanner.nextLine())) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = simpleDateFormat.parse(line);
            long ts = date.getTime();
            System.out.println(ts);
        }
        System.out.println("结束运行 bey~~");
    }
}