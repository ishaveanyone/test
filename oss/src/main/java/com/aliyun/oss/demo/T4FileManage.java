package com.aliyun.oss.demo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

import java.util.List;

public class T4FileManage {
    private static String endpoint = "";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    public static void main(String[] args) {

//        exsitFile();
//        fileAcl();
        listAllFile();
    }


    /****
     *
     *
     *
     访问权限	描述	访问权限值
     继承Bucket	文件遵循存储空间的访问权限。	CannedAccessControlList.Default
     私有	文件的拥有者和授权用户有该文件的读写权限，其他用户没有权限操作该文件。	CannedAccessControlList.Private
     公共读	文件的拥有者和授权用户有该文件的读写权限，其他用户只有文件的读权限。请谨慎使用该权限。	CannedAccessControlList.PublicRead
     公共读写	所有用户都有该文件的读写权限。请谨慎使用该权限。	CannedAccessControlList.PublicReadWrite
     */
    static void  fileAcl(){

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 设置文件的访问权限为公共读。
        ossClient.setObjectAcl("xupptest", "a/b/test.pdf", CannedAccessControlList.PublicRead);

// 关闭OSSClient。
        ossClient.shutdown();
    }
    static void exsitFile(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        boolean found = ossClient.doesObjectExist("xupptest", "a/b/test.pdf");
        boolean found2 = ossClient.doesObjectExist("xupptest", "key");
        System.out.println(found);
        System.out.println(found2);
// 关闭OSSClient。
        ossClient.shutdown();
    }


    static void listAllFile(){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 列举文件。 如果不设置KeyPrefix，则列举存储空间下所有的文件。KeyPrefix，则列举包含指定前缀的文件。
        ObjectListing objectListing = ossClient.listObjects("xupptest", "");

        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
//            s.sums
            System.out.println(s.getKey());
            System.out.println("\t" + s.getKey());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
