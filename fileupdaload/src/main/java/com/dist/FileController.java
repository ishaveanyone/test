package com.dist;


import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController("FileController")
@RequestMapping(value = "rest/file")
public class FileController{

    @Value("${temp_file_store_path}")
    private String temp_file_store_path;

    @Value("${temp_file_merge_path}")
    private String temp_file_merge_path;


    /**
     * todo 断点续传功能还没有完成
     * @param request
     * @param response
     * @param guid
     * @param chunk
     * @param file
     */
    @PostMapping(value = "/upload")
    public void uploadingPost(
            HttpServletRequest request,//request
            HttpServletResponse response,//response
            String guid,//用于标志每一个大文件进行临时存储的磁盘目录
            Integer chunk,//分片的下标
            MultipartFile file//具体文件
    ) {


        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                String tempFileDir = temp_file_store_path + guid;
                File parentFileDir = new File(tempFileDir);
                if (!parentFileDir.exists()) {
                    parentFileDir.mkdirs();
                }
                // 分片处理时，前台会多次调用上传接口，每次都会上传文件的一部分到后台
                File tempPartFile = new File(parentFileDir, guid + "_" + chunk + ".part");
                FileUtils.copyInputStreamToFile(file.getInputStream(), tempPartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @PostMapping( "/merge")
    public String mergeFile(@RequestParam String guid,@RequestParam String fileName){
        try {
            File parentFileDir = new File(temp_file_store_path + guid);
            if(parentFileDir.isDirectory()){
                File destTempFile = new File(temp_file_merge_path+guid+File.separator,
                        fileName);
                if(!destTempFile.exists()){
                    //先得到文件的上级目录，并创建上级目录，在创建文件,
                    destTempFile.getParentFile().mkdir();
                    try {
                        //创建文件
                        destTempFile.createNewFile(); //上级目录没有创建，这里会报错
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                for (int i = 0; i < parentFileDir.listFiles().length; i++) {
                    File partFile = new File(parentFileDir, guid + "_" + i + ".part");
                    FileOutputStream destTempfos = new FileOutputStream(destTempFile,
                            true);
                    //遍历"所有分片文件"到"最终文件"中
                    FileUtils.copyFile(partFile, destTempfos);
                    destTempfos.close();
                }
            }
            return "success";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping( "/checkUploaded")
    public String checkUploaded(
            @RequestParam String guid,
            @RequestParam Integer chunk,
            @RequestParam Integer chunkSize){
        String tempFileDir = temp_file_store_path + guid;
        File parentFileDir = new File(tempFileDir);
        //先判断文件夹是否存在不存在那么就直接返回
        if(!parentFileDir.exists()){
            return "error";
        }
        //判断分段文件是否存在
        File tempPartFile = new File(parentFileDir, guid + "_" + chunk + ".part");
        if(!tempPartFile.exists()){
            return "error";
        }
        //判断文件是否是完整的
       if(tempPartFile.length()!=chunkSize){
            return "error";
       }
       return "success";
    }

    //文件下载
    @GetMapping("/document/file/download/{fileId}")
    public String downloadFile(
            @PathVariable String fileId
    ) throws Exception{

        return "";
    }

}
