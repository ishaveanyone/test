package com.dist;

import jdk.internal.util.xml.impl.Input;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Response {

    private OutputStream outputStream;

    private Request request;


    public Response(OutputStream outputStream){
        this.outputStream=outputStream;
    }

    public void setRequest(Request request){
        this.request=request;
    }

    public void sendStaticResource() throws URISyntaxException, IOException {

        //找到对应的文件
       String targetFilePath=HttpServer.webpath+request.getUri();

       if(new File(targetFilePath).exists()){
           System.out.println(request.getUri());
           //必须包含返回头，否则浏览器不识别
           outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
           //一个\r\n代表换行添加新的头，2次\r\n代表头结束
           outputStream.write("Content-Type: text/html\r\n\r\n".getBytes());
           InputStream in=new FileInputStream(targetFilePath);
           File file= new File(targetFilePath);
           byte[] bytes=new byte[(int) file.length()];
           int read=0;
           while((read=in.read(bytes))!=-1){
           }
           outputStream.write(bytes);
       }else{
           //必须包含返回头，否则浏览器不识别
           outputStream.write("HTTP/1.1 404 OK\r\n".getBytes());
           //一个\r\n代表换行添加新的头，2次\r\n代表头结束
           outputStream.write("Content-Type: text/html\r\n\r\n".getBytes());
           outputStream.write("<h1>file not found</h1>".getBytes());
       }
    }
}
