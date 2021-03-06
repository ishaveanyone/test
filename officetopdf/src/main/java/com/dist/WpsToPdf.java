/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-01-07 10:10
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 *//*


package com.dist;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import sun.plugin2.util.SystemUtil;

import java.io.File;
import java.util.Date;
import java.util.logging.Logger;

*/
/**
 * @author Administrator 需要把动态库：jacob-1.18-x64.dll
 * 单元测试需要手动放入至测试路径下 支持wps 将word转pdf
 *//*

public class WpsToPdf {
    private static final int wdFormatPDF = 17;
    private static final int xlTypePDF = 0;
    private static final int ppSaveAsPDF = 32;

    */
/**
     * 转换pdf
     * @param inputFile
     * @param pdfFile
     * @return
     *//*

    public static boolean convert2PDF(String inputFile, String pdfFile) {
        String suffix = getFileSufix(inputFile);
        String newInputFile = "";
        String newPdfFile = "";
        // 如果文件路径中含有空格，则需要先替换掉
        if (inputFile.contains(" ")) {
            newInputFile = inputFile.replace(" ", "");
            newPdfFile = pdfFile.replace(" ", "");
            File dir = new File(newInputFile.substring(0,newInputFile.lastIndexOf(File.separator)));
            if (!dir.isDirectory())
                dir.mkdirs();
            SystemUtil.copyFile(inputFile, newInputFile);
            File file = new File(newInputFile);
            if (!file.exists()) {
                System.out.println("文件不存在！");
                logger.error("文件不存在！");
                return false;
            }
            if (suffix.equals("pdf")) {
                System.out.println("PDF not need to convert!");
                logger.error("PDF not need to convert!");
                return false;
            }
            if (suffix.equals("doc") || suffix.equals("docx")
                    || suffix.equals("txt")) {
                boolean flag = word2PDF(newInputFile, newPdfFile);
                new File(newInputFile).delete();
                if (flag)
                    SystemUtil.copyFile(newPdfFile, pdfFile);
                new File(newPdfFile).delete();
                return flag;
            } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
                boolean flag = ppt2PDF(newInputFile, newPdfFile);
                new File(newInputFile).delete();
                if (flag)
                    SystemUtil.copyFile(newPdfFile, pdfFile);
                new File(newPdfFile).delete();
                return flag;
            } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
                boolean flag = Ex2PDF(newInputFile, newPdfFile);
                new File(newInputFile).delete();
                if (flag)
                    SystemUtil.copyFile(newPdfFile, pdfFile);
                new File(newPdfFile).delete();
                return flag;
            } else {
                System.out.println("文件格式不支持转换!");
                logger.error("文件格式不支持转换!");
                return false;
            }
        } else {
            File file = new File(inputFile);
            if (!file.exists()) {
                System.out.println("文件不存在！");
                logger.error("文件不存在！");
                return false;
            }
            if (suffix.equals("pdf")) {
                System.out.println("PDF not need to convert!");
                logger.error("PDF not need to convert!");
                return false;
            }
            if (suffix.equals("doc") || suffix.equals("docx")
                    || suffix.equals("txt")) {
                return word2PDF(inputFile, pdfFile);
            } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
                return ppt2PDF(inputFile, pdfFile);
            } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
                return Ex2PDF(inputFile, pdfFile);
            } else {
                System.out.println("文件格式不支持转换!");
                logger.error("文件格式不支持转换!");
                return false;
            }
        }
    }

    public static boolean convert2PDF2(String inputFile, String pdfFile) {
//      System.out.println(System.getProperty("jacob.dll.path"));
        System.out.println("转换文件路径："+inputFile);
        String suffix = getFileSufix(inputFile);
        switch (suffix){
            case "ppt":
            case "pptx":
                return ppt2PDF(inputFile,pdfFile);
            case "doc":
            case "docx":
            case "txt":
                return word2PDF(inputFile,pdfFile);
            case "xls":
            case "xlsx":
                return Ex2PDF(inputFile,pdfFile);
        }
        System.out.println("文件格式不支持转换");
        return false;
    }

    public static String getFileSufix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(splitIndex + 1);
    }



    */
/***
     *
     * Word转PDF
     *
     * @param inputFile
     * @param pdfFile
     * @return
     *//*


    private static Boolean word2PDF(String inputFile, String pdfFile) {
        Boolean flag=true;
        try {
            ComThread.InitSTA(false);
            // 打开Word应用程序
            ActiveXComponent app = new ActiveXComponent("KWPS.Application");
            System.out.println("开始转化Word为PDF...");
            long date = new Date().getTime();
            // 设置Word不可见
            app.setProperty("Visible", new Variant(false));
            // 禁用宏
            app.setProperty("AutomationSecurity", new Variant(3));
            // 获得Word中所有打开的文档，返回documents对象
            Dispatch docs = app.getProperty("Documents").toDispatch();
            // 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
            Dispatch doc = Dispatch.call(docs, "Open", inputFile, false, true).toDispatch();
            */
/***
             *
             * 调用Document对象的SaveAs方法，将文档保存为pdf格式
             *
             * Dispatch.call(doc, "SaveAs", pdfFile, wdFormatPDF
             * word保存为pdf格式宏，值为17 )
             *
             *//*

            Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF);// word保存为pdf格式宏，值为17
            System.out.println(doc);
            // 关闭文档
            long date2 = new Date().getTime();
            int time = (int) ((date2 - date) / 1000);

            Dispatch.call(doc, "Close", false);
            // 关闭Word应用程序
            app.invoke("Quit", 0);
            if (doc != null) {
                doc.safeRelease();
            }

            if (docs != null) {
                docs.safeRelease();
            }

            if (app != null) {
                app.safeRelease();
            }
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        } finally {
            ComThread.Release();
        }

    }

    */
/***
     *
     * Excel转化成PDF
     *
     * @param inputFile
     * @param pdfFile
     * @return
     *//*

    private static Boolean Ex2PDF(String inputFile, String pdfFile) {
        try {
            ComThread.InitSTA(false);
            ActiveXComponent ax = new ActiveXComponent("KET.Application");
            System.out.println("开始转化Excel为PDF...");
            long date = new Date().getTime();
            ax.setProperty("Visible", false);
            ax.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
            Dispatch excels = ax.getProperty("Workbooks").toDispatch();

            Dispatch excel = Dispatch
                    .invoke(excels, "Open", Dispatch.Method,
                            new Object[]{inputFile, new Variant(false), new Variant(false)}, new int[9])
                    .toDispatch();
            // 转换格式
            Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method, new Object[]{new Variant(0), // PDF格式=0
                    pdfFile, new Variant(xlTypePDF) // 0=标准 (生成的PDF图片不会变模糊) 1=最小文件
                    // (生成的PDF图片糊的一塌糊涂)
            }, new int[1]);

            // 这里放弃使用SaveAs
            */
/*
             * Dispatch.invoke(excel,"SaveAs",Dispatch.Method,new Object[]{
             * outFile, new Variant(57), new Variant(false), new Variant(57),
             * new Variant(57), new Variant(false), new Variant(true), new
             * Variant(57), new Variant(true), new Variant(true), new
             * Variant(true) },new int[1]);
             *//*

            long date2 = new Date().getTime();
            int time = (int) ((date2 - date) / 1000);
            Dispatch.call(excel, "Close", new Variant(false));

            if (ax != null) {
                ax.invoke("Quit", new Variant[]{});
                ax = null;
            }

            if (excel != null) {
                excel.safeRelease();
            }

            if (excels != null) {
                excels.safeRelease();
            }

            if (ax != null) {
                ax.safeRelease();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        } finally {
            ComThread.Release();
        }
    }

    */
/***
     * ppt转化成PDF
     *
     * @param inputFile
     * @param pdfFile
     * @return
     *//*

    private static Boolean ppt2PDF(String inputFile, String pdfFile) {
        try {
            ComThread.InitSTA(false);
            ActiveXComponent app = new ActiveXComponent("KWPP.Application");
//            app.setProperty("Visible", false);
            System.out.println("开始转化PPT为PDF...");
            long date = new Date().getTime();
            Dispatch ppts = app.getProperty("Presentations").toDispatch();
            Dispatch ppt = Dispatch.call(ppts, "Open", inputFile, true, // ReadOnly
                    //    false, // Untitled指定文件是否有标题
                    false// WithWindow指定文件是否可见
            ).toDispatch();
            Dispatch.invoke(ppt, "SaveAs", Dispatch.Method, new Object[]{
                    pdfFile, new Variant(ppSaveAsPDF)}, new int[1]);
            System.out.println("PPT");
            Dispatch.call(ppt, "Close");
            long date2 = new Date().getTime();
            int time = (int) ((date2 - date) / 1000);
            app.invoke("Quit");

            if (ppt != null) {
                ppt.safeRelease();
            }

            if (ppts != null) {
                ppts.safeRelease();
            }

            if (app != null) {
                app.safeRelease();
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        } finally {
            ComThread.Release();
        }
    }

    public static void main(String[] args) {








    }


}
*/
