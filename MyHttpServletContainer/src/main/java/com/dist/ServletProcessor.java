package com.dist;

import com.sun.jndi.toolkit.url.UrlUtil;

import javax.servlet.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

//servlet 处理器
public class ServletProcessor {

    // 只需要进行数据 处理
    public void process(Request request,Response response){
        String uri=request.getUri();
        String serveltName=uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader urlClassLoader=null;
        try{
            URL[] urls=new URL[1];
            URLStreamHandler urlStreamHandler=null;
            File classPath=new File(Constants.WEB_APP);
            String repository=new URL("file",null,classPath.getCanonicalPath()+File.separator).toString();
            urls[0]=new URL(null,repository,urlStreamHandler);
            urlClassLoader=new URLClassLoader(urls);

        }catch (Exception e){
            e.printStackTrace();
        }
        Class myClass=null;
        try {
            urlClassLoader.loadClass(serveltName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Servlet servlet=null;
        try {
            servlet= (Servlet)myClass.newInstance();
            servlet.service((ServletRequest)request,(ServletResponse)response);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
