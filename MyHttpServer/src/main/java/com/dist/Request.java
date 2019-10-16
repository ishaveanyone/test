package com.dist;


import java.io.IOException;
import java.io.InputStream;

public class Request {

    private InputStream in;
    private String uri;

    public Request(InputStream in){
        this.in=in;
    }

    public void parse() throws IOException {
        StringBuffer sb=new StringBuffer(2048);
        byte[] buffer=new byte[2048];
        int i=in.read(buffer);

        for(int j=0;j<i;j++){
            sb.append((char)buffer[j]);
        }
        System.out.println(sb.toString());
        this.uri= parseUri(sb.toString());
    }

    private String parseUri(String requestStr){
        int index1=requestStr.indexOf(' ');
        if(index1!=-1){
            int index2=requestStr.indexOf(' ',index1+1);
            if(index2>index1){
                return requestStr.substring(index1+1,index2);
            }
        }
        return  null;
    }

    public String getUri(){
        return uri;
    }
}
