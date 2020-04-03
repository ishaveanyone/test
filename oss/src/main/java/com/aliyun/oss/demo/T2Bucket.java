package com.aliyun.oss.demo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.DateUtil;
import com.aliyun.oss.model.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class T2Bucket {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    static  String endpoint = "";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    static  String accessKeyId = "qAALhWQY2oMy1cwE";
    static String accessKeySecret = "1XfG1fugfUt8v3BfoYEnCuVHlGQrYA";
    // 创建OSSClient实例。
    public static void main(String[] args) throws ParseException {
//        getLocation();
//        checkExsits();
//        listBucket();
//        getInfo();
//        bucketAcl();
//        delBucket();//
//
//        setTag();
//
//        lifeSycle();
//        testlog();

        createBucket();
    }

    static void testlog(){


// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        SetBucketLoggingRequest request = new SetBucketLoggingRequest("disttest");
// 设置存放日志文件的存储空间。
        request.setTargetBucket("disttest");
// 设置日志文件存放的目录。
        request.setTargetPrefix("a/b/a.log");
        ossClient.setBucketLogging(request);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    static void lifeSycle() throws ParseException {


// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        SetBucketLifecycleRequest request = new SetBucketLifecycleRequest("disttest");

// 设置规则ID和文件前缀。
        String ruleId0 = "rule0";
        String matchPrefix0 = "A0/";
        String ruleId1 = "rule1";
        String matchPrefix1 = "A1/";
        String ruleId2 = "rule2";
        String matchPrefix2 = "A2/";
        String ruleId3 = "rule3";
        String matchPrefix3 = "A3/";

// 距最后修改时间3天后过期。
        request.AddLifecycleRule(new LifecycleRule(ruleId0, matchPrefix0, LifecycleRule.RuleStatus.Enabled, 3));

// 指定日期之前创建的文件过期。
        LifecycleRule rule = new LifecycleRule(ruleId1, matchPrefix1, LifecycleRule.RuleStatus.Enabled);
        rule.setCreatedBeforeDate(DateUtil.parseIso8601Date("2022-10-12T00:00:00.000Z"));
        request.AddLifecycleRule(rule);

// 分片3天后过期。
        rule = new LifecycleRule(ruleId2, matchPrefix2, LifecycleRule.RuleStatus.Enabled);
        LifecycleRule.AbortMultipartUpload abortMultipartUpload = new LifecycleRule.AbortMultipartUpload();
        abortMultipartUpload.setExpirationDays(3);
        rule.setAbortMultipartUpload(abortMultipartUpload);
        request.AddLifecycleRule(rule);

// 指定日期之前的分片过期。
        rule = new LifecycleRule(ruleId3, matchPrefix3, LifecycleRule.RuleStatus.Enabled);
        abortMultipartUpload = new LifecycleRule.AbortMultipartUpload();
        abortMultipartUpload.setCreatedBeforeDate(DateUtil.parseIso8601Date("2022-10-12T00:00:00.000Z"));
        rule.setAbortMultipartUpload(abortMultipartUpload);
        request.AddLifecycleRule(rule);

        ossClient.setBucketLifecycle(request);

// 关闭OSSClient。
        ossClient.shutdown();


    }

    static void testReferer(){

        String bucketName = "disttest";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        List<String> refererList = new ArrayList<String>();
// 添加Referer白名单。Referer参数支持通配符星号（*）和问号（？）。
        refererList.add("http://www.aliyun.com");
        refererList.add("http://www.*.com");
        refererList.add("http://www.?.aliyuncs.com");
// 设置存储空间Referer列表。设为true表示Referer字段允许为空。
        BucketReferer br = new BucketReferer(true, refererList);
        ossClient.setBucketReferer(bucketName, br);

// 关闭OSSClient。
        ossClient.shutdown();
    }


    static void setTag(){


// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 设置Bucket标签。
        SetBucketTaggingRequest request = new SetBucketTaggingRequest("disttest");//必须endpotion也是有要求
        request.setTag("tk1", "tv1");
        request.setTag("tk2", "tv2");

        TagSet set= ossClient.getBucketTagging("disttest");
        System.out.println(set);

        ossClient.setBucketTagging(request);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    static void delBucket(){

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().
                build(endpoint, accessKeyId, accessKeySecret);

// 设置存储空间的访问权限为私有。
//        ossClient.deleteBucket("cs1-46433");//必须先清空
//        ossClient.deleteBucketLifecycle("cs1-46433");//必须先清空


// 关闭OSSClient。
        ossClient.shutdown();
    }
    static  void bucketAcl(){

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().
                build(endpoint, accessKeyId, accessKeySecret);

// 设置存储空间的访问权限为私有。
        ossClient.setBucketAcl("disttest",
                CannedAccessControlList.PublicRead);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    static void getInfo(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 存储空间的信息包括地域（Region或Location）、创建日期（CreationDate）、拥有者（Owner）、权限（Grants）等。
        BucketInfo info = ossClient.getBucketInfo("xupptest");
// 获取地域。
        System.out.println(info.getBucket().getLocation());
// 获取创建日期。
        System.out.println(info.getBucket().getCreationDate());
// 获取拥有者信息。
        System.out.println(info.getBucket().getOwner());
// 获取权限信息。
//        System.out.println(info.getGrants());
        AccessControlList acl = ossClient.getBucketAcl("disttest");
        System.out.println(acl.toString());
// 获取容灾类型。
        System.out.println(   info.getDataRedundancyType());

// 关闭OSSClient。
        ossClient.shutdown();
    }


    static  void getLocation(){

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        String location = ossClient.getBucketLocation("xupptest");
        System.out.println(location);
// 关闭OSSClient。
        ossClient.shutdown();
    }

    static  void  checkExsits(){

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        System.out.println(ossClient.doesBucketExist("xupptest"));
    }

    static  void createBucket(){

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建CreateBucketRequest对象。
        /**
         * Bucket "测试" 名称无效。Bucket 命名规范：1)只能包括小写字母，数字和短横线（-）；2)必须以小写字母或者数字开头；3)长度必须在 3-63 字节之间。
         */
        CreateBucketRequest createBucketRequest = new CreateBucketRequest("lswth11");

// 如果创建存储空间的同时需要指定存储类型以及数据容灾类型, 可以参考以下代码。
// 此处以设置存储空间的存储类型为标准存储为例。
// createBucketRequest.setStorageClass(StorageClass.Standard);
// 默认情况下，数据容灾类型为本地冗余存储，即DataRedundancyType.LRS。如果需要设置数据容灾类型为同城冗余存储，请替换为DataRedundancyType.ZRS。
// createBucketRequest.setDataRedundancyType(DataRedundancyType.ZRS)

// 创建存储空间。
        ossClient.createBucket(createBucketRequest);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    static  void listBucket(){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
// 限定此次列举存储空间的个数为500。默认值为100，最大值为1000。
        listBucketsRequest.setMaxKeys(1);
//        listBucketsRequest.setPrefix("c");
        listBucketsRequest.setMarker("d");//设置指定之后的 比如a b c 这样顺序返回

// 列举存储空间。
//        List<Bucket> buckets = ossClient.listBuckets();
        BucketList bucketList = ossClient.listBuckets(listBucketsRequest);
        List<Bucket> buckets  =bucketList.getBucketList();
        for (Bucket bucket:buckets){
            System.out.println(bucket.getName());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }

}
