package com.dist.pgsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired  TResp tResp;
    @Autowired  T2Resp t2Resp;
    @Autowired  T3Resp t3Resp;
    @Autowired  T1Resp t1Resp;
    @Autowired  T5Resp t5Resp;
    @Autowired  T6Resp t6Resp;
    @GetMapping("/t")
    public T gett(){
        System.out.println(1);
        return  tResp.findById(1).get();
    }

    @GetMapping("/t2")
    public T2 gett2(){
        System.out.println(1);
        return  t2Resp.findById(1).get();
    }

    @GetMapping("/t3")
    public T3 gett3(){
        System.out.println(1);
        return  t3Resp.findById(1).get();
    }

    @GetMapping("/t1")
    public T1 gett1(){
        System.out.println(1);
        return  t1Resp.findById(1).get();
    }

    @GetMapping("/t5")
    public T5 gett5(){
        System.out.println(1);
        return  t5Resp.findById(1).get();
    }

    @GetMapping("/t6")
    public T6 gett6(){
        System.out.println(1);
        return t6Resp.findById(1).get();
    }

    @GetMapping("/t7")
    public List<T2> gett7(){
        System.out.println(1);
        return t2Resp.findAll2();
    }
    @GetMapping("/t8")
    public T2 gett8(){
        System.out.println(1);
        return t2Resp.findById(8).get();
    }


    @PostMapping("/download")
    @ResponseBody
    public String download(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        String downLoadPath = "H:/b.pdf";  //注意不同系统的分隔符
        //	String downLoadPath =filePath.replaceAll("/", "\\\\\\\\");   //replace replaceAll区别 *****
        System.out.println(downLoadPath);
        try {
            long fileLength = new File(downLoadPath).length();
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename=" + new String("b.pdf".getBytes("utf-8"), "ISO8859-1"));
            response.setHeader("Content-Length", String.valueOf(fileLength));
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        }
        return null;
    }



}


class A{
    public String name;
}

class B{
    public String name;

}