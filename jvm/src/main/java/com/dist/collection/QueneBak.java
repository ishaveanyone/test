/**
 * Date: 2020-07-22 11:30
 * Author: xupp
 */

package com.dist.collection;

import java.util.*;

public class QueneBak {
    public static void main(String[] args) {
        System.out.println(10/10);
    }
  public static class ListNode {
      int val;
     ListNode next;
      ListNode(int x) { val = x; }
  }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum=new ListNode(0);
        sum(l1,l2,sum,0);
        return sum;
    }

    public static void sum(ListNode l1, ListNode l2 ,ListNode sum,int count){

        if(l1==null&&l2!=null){
            sum=new ListNode(l2.val);
        }
        if(l2==null&&l1!=null){
            sum=new ListNode(l1.val);
        }
        if(l2==null&&l1==null){
            return;
        }
        sum=new ListNode((l1.val+l2.val+count)%10);
        count=(l1.val+l2.val+count)/10;
        sum(l1.next,l2.next,sum.next,count);
    }


}
