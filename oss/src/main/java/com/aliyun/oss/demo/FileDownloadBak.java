package com.aliyun.oss.demo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;

import java.io.*;
import java.util.Date;

public class FileDownloadBak  implements ProgressListener {

    private long bytesRead = 0;
    private long totalBytes = -1;
    private boolean succeed = false;
    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to download......");
                break;
            case RESPONSE_CONTENT_LENGTH_EVENT:
                this.totalBytes = bytes;
                System.out.println(this.totalBytes + " bytes in total will be downloaded to a local file");
                break;
            case RESPONSE_BYTE_TRANSFER_EVENT:
                this.bytesRead += bytes;
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesRead * 100.0 / this.totalBytes);
                    System.out.println(bytes + " bytes have been read at this time, download progress: " +
                            percent + "%(" + this.bytesRead + "/" + this.totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes have been read at this time, download ratio: unknown" +
                            "(" + this.bytesRead + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                System.out.println("Succeed to download, " + this.bytesRead + " bytes have been transferred in total");
                break;
            case TRANSFER_FAILED_EVENT:
                System.out.println("Failed to download, " + this.bytesRead + " bytes have been transferred");
                break;
            default:
                break;
        }
    }
    public boolean isSucceed() {
        return succeed;
    }


    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAI4FptiyDnHfYsCPoBSq4Q";
    private static String accessKeySecret = "HmGxyviLd4N7NMZP41dYa6LU48alU1";
    public static void main(String[] args) throws Throwable {
        streamDownload();
//        rangedownload();
        continueDownload();
//        conditionDownload();
    }



    //流式下载
    static  void streamDownload() throws IOException {
        String bucketName = "xupptest";
// <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "a/b/test.pdf";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();

        String path="H:/c.pdf";
        if(!new File(path).exists()){
            new File(path).createNewFile();
        }
        OutputStream out=new FileOutputStream(path);
        byte[] bytes=new byte[1024];
        if (content != null) {
            while (content.read(bytes)!=-1) {
                out.write(bytes);
            }
            out.flush();
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }
        content.close();
        out.close();
// 关闭OSSClient。
        ossClient.shutdown();
    }
    //范围下载
    static void rangedownload() throws IOException {
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        GetObjectRequest getObjectRequest = new GetObjectRequest("xupptest", "a/note.txt");
// 获取0~1000字节范围内的数据，包括0和1000，共1001个字节的数据。如果指定的范围无效（比如开始或结束位置的指定值为负数，或指定值大于文件大小），则下载整个文件。
        getObjectRequest.setRange(0, 1000);

// 范围下载。
        OSSObject ossObject = ossClient.getObject(getObjectRequest);

// 读取数据。
        String path="H:/c.txt";
        if(!new File(path).exists()){
            new File(path).createNewFile();
        }
        OutputStream out=new FileOutputStream(path);


        InputStream content = ossObject.getObjectContent();

        byte[] bytes=new byte[1024];
        if (content != null) {
            while (content.read(bytes)!=-1) {
                out.write(bytes);
            }
            out.flush();
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }

// 关闭OSSClient。
        ossClient.shutdown();

    }

    static void continueDownload() throws Throwable {
        String bucketName = "xupptest";
        String objectName = "a/b/d.zip";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 下载请求，10个任务并发下载，启动断点续传。
        DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
        downloadFileRequest.setDownloadFile("H:/f.zip");
        downloadFileRequest.setPartSize(1 * 1024 * 1024);
        downloadFileRequest.setTaskNum(10);
        downloadFileRequest.setEnableCheckpoint(true);
        downloadFileRequest.setCheckpointFile("H:/f.ucp");

// 下载文件。
//        ossClient.getObject(new GetObjectRequest(bucketName, objectName).
//                        <GetObjectRequest>withProgressListener(new FileDownloadBak())
//                );
        DownloadFileResult downloadRes =
                ossClient.downloadFile(downloadFileRequest.withProgressListener(new FileDownloadBak()));
//// 下载成功时，会返回文件元信息。
        downloadRes.getObjectMetadata();

// 关闭OSSClient。
        ossClient.shutdown();
    }


    /****
     * If-Modified-Since	如果指定的时间早于实际修改时间，则正常传输文件，否则返回错误（304 Not modified）。
     * If-Unmodified-Since	如果指定的时间等于或者晚于文件实际修改时间，则正常传输文件，否则返回错误（412 Precondition failed）。
     * If-Match	如果指定的ETag和OSS文件的ETag匹配，则正常传输文件，否则返回错误（412 Precondition failed）。
     * If-None-Match	如果指定的ETag和OSS文件的ETag不匹配，则正常传输文件，否则返回错误（304 Not modified）。
     *
     *
     *
     */
    static  void  conditionDownload(){

        String bucketName = "xupptest";
        String objectName = "a/b/test.pdf";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
// 设置限定条件。
        request.setModifiedSinceConstraint(new Date("2015/09/02"));

// 下载OSS文件到本地文件。
        ossClient.getObject(request, new File("H:/b.pdf"));

// 关闭OSSClient。
        ossClient.shutdown();
    }


}
