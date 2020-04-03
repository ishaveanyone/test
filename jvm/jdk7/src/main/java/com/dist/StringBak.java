package com.dist;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

class ListNode {
   public int val;
   public ListNode next;
   public ListNode(int x) { val = x; }
 }
public class StringBak {

    public static void main(String[] args) {
        ListNode l1=new ListNode(9);
        l1.next=new ListNode(9);
        ListNode l2=new ListNode(9);
        add(l1,l2);
        System.out.println(l1);
    }

    public static ListNode add(ListNode l1,ListNode l2){

        if(l1==null&&l2==null){
            return l1;
        }
        if(l1==null){
            l1=new ListNode(0);
        }
        if(l2==null){
            l2=new ListNode(0);
        }
        l1.val=l2.val+l1.val;
        if(l1.val>=10){
            if(l1.next!=null){
                l1.next.val=l1.next.val+l1.val/10;
            }else{
                l1.next=new ListNode(l1.val/10);
            }
            l1.val=l1.val%10;
        }
        ListNode newnode=add(l1.next,l2.next);
        l1.next=newnode;
        return l1;
    }








}
