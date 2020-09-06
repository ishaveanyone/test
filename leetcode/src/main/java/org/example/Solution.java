package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
class Solution {
   /* public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums=new int[nums1.length+nums2.length];
        int i=0,j=0,k=0;
        for(;i<nums1.length&&j<nums2.length;k++){
            if(nums1[i]<nums2[j]){
                nums[k]=nums1[i];
                i++;
            }else if(nums1[i]==nums2[j]){
                nums[k]=nums1[i];
                i++;
                j++;
            }else{
                nums[k]=nums2[j];
                j++;
            }
        }
    }*/

    public static void main(String[] args) throws IOException {
        FileReader fileReader=new FileReader(new File("H:/a.txt"));
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        String s="";
        String temp="";
        while((temp=bufferedReader.readLine())!=null){
            System.out.println(temp);
            s+=temp;
        }

    }
}