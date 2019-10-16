package com.dist;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;

/**
 * 添加 处理服务
 */
public class HttpServer {


    //定义关闭命令
    private static final String command="/SHUTDOWN";

    private static boolean isStoped=false;


    public static void main(String[] args) {
        System.out.println(Constants.WEB_APP);
        HttpServer server=new HttpServer();
        try {
            server.await();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void await() throws IOException {
        ServerSocket serverSocket=new ServerSocket(8080);
        while (!isStoped){
            Socket socket = serverSocket.accept();
            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
            Request request=new Request(inputStream);
            //解析请求
            request.parse();
            Response response=new Response(outputStream);
            response.setRequest(request);

            if(request.getUri().startsWith("/servlet")){
                ServletProcessor servletProcessor=new ServletProcessor();
                servletProcessor.process(request,response);
            }else{
                StaticResourceProcessor processor=new StaticResourceProcessor();
                processor.process(request,response);
            }

            try {
                response.sendStaticResource();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            socket.close();
            isStoped=command.equalsIgnoreCase(request.getUri());
        }
    }
}
