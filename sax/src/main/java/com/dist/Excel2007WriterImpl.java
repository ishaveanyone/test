package com.dist;

/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-09-05 17:39
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

public class Excel2007WriterImpl {


    /**
     * 测试方法
     */
    public static void main(String[] args) throws Exception {

        String file = "G:/导出测试数据.xlsx";

        Excel2007Writer writer = new Excel2007Writer() {

            public void generate() throws Exception {

                // 电子表格开始
                this.beginSheet();

                for (int rownum = 0; rownum < 100; rownum++) {
                    // 插入新行
                    this.insertRow(rownum);

                    // 建立新单元格,索引值从0开始,表示第一列
                    this.createCell(0, "第 " + rownum + " 行");
                    this.createCell(1, 34343.123456789);
                    this.createCell(2, "23.67%");
                    this.createCell(3, "12:12:23");
                    this.createCell(4, "2014-10-11 12:12:23");
                    this.createCell(5, "true");
                    this.createCell(6, "false");

                    // 结束行
                    this.endRow();
                }

                // 电子表格结束
                this.endSheet();
            }
        };
        writer.process(file);
    }


}