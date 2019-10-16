package com.dist;

import java.io.IOException;
import java.net.URISyntaxException;

//静态资源处理器
public class StaticResourceProcessor {

    public void process(Request request,Response response){
        try {
            response.sendStaticResource();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
