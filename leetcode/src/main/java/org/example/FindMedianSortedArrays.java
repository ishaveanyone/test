/**
 * Date: 2020-09-05 9:27
 * Author: xupp
 */

package org.example;

import java.security.PublicKey;

public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] nums1={1,2};
        int[] nums2={1,2};
        System.out.println( findMedianSortedArrays(nums1,nums2));
    }
    public static   double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i=0;
        int j=0;
        int[] total=new int[nums1.length+nums2.length];
        for(int index=0;i<nums1.length||j< nums2.length;){
            int target1=0;
            int target2=0;
            if(i==nums1.length){
                target1=Integer.MAX_VALUE;
            }else {
                target1=nums1[i];
            }
            if(j==nums2.length){
                target2=Integer.MAX_VALUE;
            }else{
                target2=nums2[j];
            }
            if(i==nums1.length&&j==nums2.length){
                break;
            }

            if(target1<target2){
                total[index]=target1;
                i++;
            }else if(target1==target2){
                total[index]=target1;
                total[++index]=target2;
                i++;
                j++;
            }else{
                total[index]=target2;
                j++;
            }
            index++;

        }
        if(total.length%2==0){
            return 0.0+((double)(total[total.length/2-1]+total[total.length/2]))/2;
        }else{
            return total[(total.length/2)];
        }
    }

}
