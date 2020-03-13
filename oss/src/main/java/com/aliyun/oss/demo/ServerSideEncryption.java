/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2019-12-20 9:44
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.aliyun.oss.demo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;

import java.io.ByteArrayInputStream;

/**
 * 服务端加密
 */
public class ServerSideEncryption {

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "LTAI4FptiyDnHfYsCPoBSq4Q";
    private static String accessKeySecret = "HmGxyviLd4N7NMZP41dYa6LU48alU1";

    public static void main(String[] args) {

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 设置Bucket加密。
        ServerSideEncryptionByDefault applyServerSideEncryptionByDefault = new ServerSideEncryptionByDefault(SSEAlgorithm.AES256);
        ServerSideEncryptionConfiguration sseConfig = new ServerSideEncryptionConfiguration();
        sseConfig.setApplyServerSideEncryptionByDefault(applyServerSideEncryptionByDefault);
        SetBucketEncryptionRequest request = new SetBucketEncryptionRequest("sfqe", sseConfig);
        ossClient.setBucketEncryption(request);






// 创建PutObjectRequest对象。
        String content = "Hello OSS";
// <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest("sfqe", "keyyyy", new ByteArrayInputStream(content.getBytes()));
// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);

// 上传字符串。
        ossClient.putObject(putObjectRequest);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
