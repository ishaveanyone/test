<%@ page import="com.raqsoft.report.usermodel.Context" %>
<%@ page import="com.raqsoft.report.model.ReportDefine" %>
<%@ page import="com.raqsoft.report.util.ReportUtils" %>
<%@ page import="com.raqsoft.report.usermodel.Engine" %>
<%@ page import="com.raqsoft.report.usermodel.IReport" %>
<%@ page import="java.nio.file.Files" %>
<%@ page import="java.io.*" %>
<%@ page import="java.nio.file.Path" %>
<%@ page contentType="text/html;charset=UTF-8"
         language="java" isELIgnored="false" %>
<%
    String type =request.getParameter("type");
    String gcbh = request.getParameter("gcbh");
    String reportFile = "D://Tomcat//rqbb//" + type + ".rpx";
    Context cxt = new Context();
    ReportDefine rd =(ReportDefine) ReportUtils.read(reportFile);
    cxt.setParamValue("gcbh", gcbh);
    Engine engine = new Engine(rd, cxt);
    IReport iReport = engine.calc();
    Path filedownload =Files.createTempFile(type,".xls");
    ReportUtils.exportToExcel(filedownload.toFile().getAbsolutePath(),iReport, false);
    response.setContentType("applicaiton/x-download");
    response.addHeader("Content-Disposition", "attachment;filename="+type+".xls");
    InputStream is = null;
    OutputStream os = null;
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    is = new FileInputStream(filedownload.toFile());
    bis = new BufferedInputStream(is);
    os = response.getOutputStream();
    bos = new BufferedOutputStream(os);
    byte[] b = new byte[1024];
    int len = 0;
    while((len = bis.read(b)) != -1){
        bos.write(b,0,len);
    }
    bis.close();
    is.close();
    bos.close();
    os.close();
%>

