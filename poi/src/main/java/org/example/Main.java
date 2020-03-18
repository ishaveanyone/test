/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-03-16 17:33
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package org.example;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        //读取本地文件
        File file = new File("H:/test.xlsx");
        FileInputStream fis = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet1 = workbook.getSheetAt(0);


        sheet1.shiftRows(1,sheet1.getLastRowNum(),1,true,false);
        CellRangeAddress cellRangeAddress1=new CellRangeAddress(4,5,0,0);
        CellRangeAddress cellRangeAddress2=new CellRangeAddress(0,1+1,1,1);
        sheet1.addMergedRegion(cellRangeAddress1);
//        sheet1.addMergedRegion(cellRangeAddress2);

        FileOutputStream myxlsout = new FileOutputStream("H:/test.xlsx");
        workbook.write(myxlsout);
        myxlsout.close();
    }


    public static String getCellValue(Cell cell) {
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            Double d = cell.getNumericCellValue();
            return String.valueOf(d.intValue());
        }
        return String.valueOf(cell.getStringCellValue());
    }

}
