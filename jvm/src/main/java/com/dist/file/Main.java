/**
 * Company: 上海数慧系统技术有限公司
 * Department: 数据中心
 * Date: 2020-03-19 16:54
 * Author: xupp
 * Email: xupp@dist.com.cn
 * Desc：
 */

package com.dist.file;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //就是一个排序算法 折中插入排序算法
        int[] newArray=new int[nums1.length+nums2.length];
        for(int i=0;i<nums1.length;i++){
            newArray[i]=nums1[i];
        }
        for(int i=nums1.length;i<nums2.length+nums1.length;i++){
            newArray[i]=nums2[i-nums1.length];
        }
        //进行排序
        //默认是前 i （i>0）个元素已经完成了排序 对 i>=1 个元素进行排序
        for(int i=1;i<newArray.length;i++){
            int low =0;
            int high=i-1;
            //目标元素
            int target=newArray[i];
            //前面元素的 中间元素
            while(low<=high){
                int mid=newArray[(low+high)/2];
                if(target>mid){
                    low=(low+high)/2+1;
                }else{
                    high=(low+high)/2-1;
                }
            }
            //进行数据平移
            for(int j=i;j>low;j--){
                newArray[j]=newArray[j-1];
            }
            newArray[low]=target;
        }
        //进行中位数计算
        if(newArray.length%2==0){
            //偶数
            return (newArray[newArray.length/2-1]+newArray[newArray.length/2])/2;
        }else{
            return newArray[newArray.length/2];
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        nioDownload();
        aioFileToStream();
    }


    //使用nio 进行文件下载

    public static void nioDownload() throws IOException, InterruptedException {
        String filePath="H:/c.zip"; // 创建源文件
        String tartFile="H:/c3.zip"; // 创建源文件
        ByteBuffer buffer = ByteBuffer.allocate(1024);//创建 缓冲区
        RandomAccessFile file = new RandomAccessFile(filePath, "rw");
        FileChannel fc = file.getChannel();
        FileOutputStream fileOutputStream=new FileOutputStream(tartFile);
        while ((fc.read(buffer)) >= 0) {
            //翻转指针
            buffer.flip();
            //remaining = limit - position
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            fileOutputStream.write(bytes);
            //清空buffer
            buffer.clear();
//            Thread.sleep(1000);
        }

    }


    public static void aioFileToStream() throws IOException, InterruptedException {
        String filePath="H:/c.zip"; // 创建源文件
        String tartFile="H:/c3.zip"; // 创建源文件

        FileInputStream fin=new FileInputStream(filePath);
        FileOutputStream fout=new FileOutputStream(tartFile);
        byte[] bytes=new byte[290];
        while(fin.read(bytes)!=-1){

            fout.write(bytes);
            bytes=new byte[290];
//            Thread.sleep(1000);
        }


    }
}
