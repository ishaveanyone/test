package com.aliyun.oss.demo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class T3FileUploadBak {

    private static String endpoint = "";

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private static String accessKeyId = "";
    private static String accessKeySecret = "";

    public static void main(String[] args) throws Throwable {
        appendUpload1();
    }


   public static void   appendUpload0(){
        OSS ossClient = new OSSClientBuilder().
                build(endpoint, accessKeyId, accessKeySecret);
       AppendObjectRequest appendObjectRequest =
               new AppendObjectRequest("lshw", "a/b",new File("H:/easy.txt"));
       appendObjectRequest.setPosition(0L);//初始文件那么在开始位置进行文件添加
       AppendObjectResult appendObjectResult =
               ossClient.appendObject(appendObjectRequest);
       ossClient.shutdown();

    }

    public static void   appendUpload1(){
        OSS ossClient = new OSSClientBuilder().
                build(endpoint, accessKeyId, accessKeySecret);
        AppendObjectRequest appendObjectRequest =
                new AppendObjectRequest("lshw", "a/b",new File("H:/easy.txt"));
        appendObjectRequest.setPosition(258012L);//初始文件那么在开始位置进行文件添加
        AppendObjectResult appendObjectResult =
                ossClient.appendObject(appendObjectRequest);
        ossClient.shutdown();

    }








    public static void list(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 列举文件。 如果不设置KeyPrefix，则列举存储空间下所有的文件。KeyPrefix，则列举包含指定前缀的文件。
        ObjectListing objectListing = ossClient.listObjects("ghxt-oss");
        List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
        for (OSSObjectSummary s : sums) {
            System.out.println("\t" + s.getKey());
        }
// 关闭OSSClient。
        ossClient.shutdown();


    }


    static void uploadContinue() throws Throwable {
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectMetadata meta = new ObjectMetadata();
// 指定上传的内容类型。
        meta.setContentType("text/plain");

// 通过UploadFileRequest设置多个参数。
        UploadFileRequest uploadFileRequest = new
                UploadFileRequest("dist-oss","a/b/c.zip");

// 通过UploadFileRequest设置单个参数。
// 设置存储空间名称。
//uploadFileRequest.setBucketName("<yourBucketName>");
// 设置文件名称。
//uploadFileRequest.setKey("<yourObjectName>");
// 指定上传的本地文件。
        uploadFileRequest.setUploadFile("H:/t.zip");
// 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
// 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
// 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);

// 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
        uploadFileRequest.setCheckpointFile("H:/c.ucp");
// 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
// 设置上传成功回调，参数为Callback类型。
//        uploadFileRequest.setCallback(new Callback());

// 断点续传上传。
        ossClient.uploadFile(uploadFileRequest.withProgressListener(new PutObjectProgressListener()));

// 关闭OSSClient。
        ossClient.shutdown();

    }

    static  void listUploadedPartFile(String bucketname,String objectname,String uploadid){
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 列举已上传的分片，其中uploadId来自于InitiateMultipartUpload返回的结果。
        ListPartsRequest listPartsRequest = new ListPartsRequest(bucketname, objectname, uploadid);
        // 设置uploadId。
        //listPartsRequest.setUploadId(uploadId);
        // 设置分页时每一页中分片数量为100个。默认列举1000个分片。
        listPartsRequest.setMaxParts(100);
        // 指定List的起始位置。只有分片号大于该参数值的分片会被列出。
        listPartsRequest.setPartNumberMarker(1);
        PartListing partListing = ossClient.listParts(listPartsRequest);

        for (PartSummary part : partListing.getParts()) {
            // 获取分片号。
            System.out.println("part number:>>>"+part.getPartNumber());
            // 获取分片数据大小。
//            System.out.println(part.getSize());
            // 获取分片的ETag。
//            System.out.println(part.getETag());
            // 获取分片的最后修改时间。
//            System.out.println( part.getLastModified());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }

    //断电之后仍然存在
    static  void partUpload() throws IOException {

        String objectName = "a/b/c.zip";//虚拟路径

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建InitiateMultipartUploadRequest对象。
        InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest("ghxt-oss", objectName);

// 如果需要在初始化分片时设置文件存储类型，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// request.setObjectMetadata(metadata);

// 初始化分片。
        InitiateMultipartUploadResult upresult = ossClient.initiateMultipartUpload(request);
// 返回uploadId，它是分片上传事件的唯一标识，您可以根据这个ID来发起相关的操作，如取消分片上传、查询分片上传等。
        String uploadId = "B40561BC1D73496D8F021A7F5BC17EEC";
        System.out.println(uploadId);

// partETags是PartETag的集合。PartETag由分片的ETag和分片号组成。
        List<PartETag> partETags =  new ArrayList<PartETag>();
// 计算文件有多少个分片。
        final long partSize = 1 * 1024 * 1024L;   // 1MB
        final File sampleFile = new File("H:/f.zip");
        long fileLength = sampleFile.length();
        System.out.println(fileLength);
        int partCount = (int) (fileLength / partSize);
        System.out.println(partCount);
        if (fileLength % partSize != 0) {
            partCount++;
        }
// 遍历分片上传。
        ListPartsRequest listPartsRequest = new ListPartsRequest("dist-oss", objectName, uploadId);


        // 设置uploadId。
        //listPartsRequest.setUploadId(uploadId);
        // 设置分页时每一页中分片数量为100个。默认列举1000个分片。
        listPartsRequest.setMaxParts(100);
        // 指定List的起始位置。只有分片号大于该参数值的分片会被列出。
        listPartsRequest.setPartNumberMarker(1);
        PartListing partListing = ossClient.listParts(listPartsRequest);
        int startIndex=(int) partListing.getParts().stream().map(PartSummary::getPartNumber).count();

        System.out.println("startIndex:>>"+startIndex);
        for (int i = startIndex; i < partCount; i++) {


            long startPos = i * partSize;
            long curPartSize = (i + 1 == partCount) ? (fileLength - startPos) : partSize;
            InputStream instream = new FileInputStream(sampleFile);
            // 跳过已经上传的分片。
            System.out.println("start:>>"+i);
            instream.skip(startPos);
            UploadPartRequest uploadPartRequest = new UploadPartRequest();
            uploadPartRequest.setBucketName("dist-oss");
            uploadPartRequest.setKey(objectName);
            uploadPartRequest.setUploadId(uploadId);
            uploadPartRequest.setInputStream(instream);
            // 设置分片大小。除了最后一个分片没有大小限制，其他的分片最小为100KB。
            uploadPartRequest.setPartSize(curPartSize);
            // 设置分片号。每一个上传的分片都有一个分片号，取值范围是1~10000，如果超出这个范围，OSS将返回InvalidArgument的错误码。
            uploadPartRequest.setPartNumber( i + 1);
            // 每个分片不需要按顺序上传，甚至可以在不同客户端上传，OSS会按照分片号排序组成完整的文件。
            UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
            // 每次上传分片之后，OSS的返回结果会包含一个PartETag。PartETag将被保存到partETags中。
            partETags.add(uploadPartResult.getPartETag());
            System.out.println("finsh》》》"+i);
        }


// 创建CompleteMultipartUploadRequest对象。
// 在执行完成分片上传操作时，需要提供所有有效的partETags。OSS收到提交的partETags后，会逐一验证每个分片的有效性。当所有的数据分片验证通过后，OSS将把这些分片组合成一个完整的文件。
        CompleteMultipartUploadRequest completeMultipartUploadRequest =
                new CompleteMultipartUploadRequest("dist-oss", objectName, uploadId, partETags);

// 如果需要在完成文件上传的同时设置文件访问权限，请参考以下示例代码。
// completeMultipartUploadRequest.setObjectACL(CannedAccessControlList.PublicRead);

// 完成上传。
        CompleteMultipartUploadResult completeMultipartUploadResult = ossClient.completeMultipartUpload(completeMultipartUploadRequest);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    static void putObject(){
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建PutObjectRequest对象。
        String content = "Hello OSS";
// <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest("ghxt-oss", "keyyyy", new ByteArrayInputStream(content.getBytes()));
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

//文件类型：只有通过追加上传创建的文件才可以后续继续被追加上传。也就是说，其他通过简单上传、表单上传、分片上传得到的文件，不能在这些文件后面追加上传新的内容。
        //
    static void appendTest(){



        String content1 = "Hello OSS A \n";
        String content2 = "Hello OSS B \n";
        String content3 = "Hello OSS C \n";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectMetadata meta = new ObjectMetadata();
// 指定上传的内容类型。
        meta.setContentType("text/plain");
// 通过AppendObjectRequest设置多个参数。
        AppendObjectRequest appendObjectRequest = new AppendObjectRequest("xupptest", "key1", new ByteArrayInputStream(content1.getBytes()),meta);

// 通过AppendObjectRequest设置单个参数。
// 设置存储空间名称。
//appendObjectRequest.setBucketName("<yourBucketName>");
// 设置文件名称。
//appendObjectRequest.setKey("<yourObjectName>");
// 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为InputStream类型。
//appendObjectRequest.setInputStream(new ByteArrayInputStream(content1.getBytes()));
// 设置待追加的内容。有两种可选类型：InputStream类型和File类型。这里为File类型。
//appendObjectRequest.setFile(new File("<yourLocalFile>"));
// 指定文件的元信息，第一次追加时有效。
//appendObjectRequest.setMetadata(meta);

// 第一次追加。
// 设置文件的追加位置。
        appendObjectRequest.setPosition(0L);
        AppendObjectResult appendObjectResult = ossClient.appendObject(appendObjectRequest);
// 文件的64位CRC值。此值根据ECMA-182标准计算得出。
        System.out.println(appendObjectResult.getObjectCRC());

// 第二次追加。
// nextPosition指明下一次请求中应当提供的Position，即文件当前的长度。
        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
        appendObjectRequest.setInputStream(new ByteArrayInputStream(content2.getBytes()));
        appendObjectResult = ossClient.appendObject(appendObjectRequest);

// 第三次追加。
        appendObjectRequest.setPosition(appendObjectResult.getNextPosition());
        appendObjectRequest.setInputStream(new ByteArrayInputStream(content3.getBytes()));
        appendObjectResult = ossClient.appendObject(appendObjectRequest);

// 关闭OSSClient。
        ossClient.shutdown();
    }


    static void uploadFile() throws IOException {
        File file=new File("H:/aa.md");

        InputStream inputStream=new FileInputStream(file);
        String bucketName = "ghxt-oss";
// <yourObjectName>上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "a/b";//这个是 云上的放的路径
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        byte[] bytes=new byte[(int)file.length()];
        inputStream.read(bytes);
        ossClient.putObject(bucketName, objectName,
                new ByteArrayInputStream(bytes));
// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static  void download() throws IOException {
        String bucketName = "ghxt-oss";
// <yourObjectName>从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        String objectName = "a/b";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
// 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();

        String path="H:/c.md";
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
        out.close();

// 关闭OSSClient。
        ossClient.shutdown();
    }

    //追加上传断点续传
    public static void getsize(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        while(true){
            SimplifiedObjectMeta simplifiedObjectMeta=  ossClient.getSimplifiedObjectMeta("ghxt-oss","a3391380-3fb9-48e5-83d4-ee7a197a2330");
            System.out.println(simplifiedObjectMeta.getSize());

        }

    }
}
