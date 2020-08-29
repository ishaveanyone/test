package org.example;

import java.util.ArrayList;

//  Definition for singly-linked list.
   public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
class Solution {

    public static void main(String[] args) {
        ListNode l1=new ListNode(1);
        ListNode l2=new ListNode(9);
        ListNode l3=new ListNode(9);
        l2.next=l3;

        ListNode l4= new Solution().addTwoNumbers(l1,l2);
        System.out.println(l4);
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode sum=new ListNode(0);
        if(l1!=null||l2!=null){
            addTwoNumbers(sum,l1,l2);
        }
        ListNode newHead = reverse(sum);

        while(newHead!=null){
            if(newHead.val==0){
                newHead=newHead.next;
            }else{
                break;
            }
        }

        ArrayList

        if(newHead==null){
            return new ListNode(0);
        }
        newHead = reverse(newHead);
        deal(newHead,0);
        //处理

        return newHead;
    }
    public void addTwoNumbers(ListNode sum,ListNode l1, ListNode l2) {
        if(l1==null&&l2==null){
            return;
        }else{
            sum.next=new ListNode(0);
        }
        if(l1==null&&l2!=null){
            sum.val=l2.val;
            addTwoNumbers(sum.next,null,l2.next);
        }
        if(l1!=null&&l2==null)
        {
            sum.val=l1.val;
            addTwoNumbers(sum.next,l1.next,null);
        }
        if(l1!=null&&l2!=null)
        {
            sum.val=l1.val+l2.val;
            addTwoNumbers(sum.next,l1.next,l2.next);
        }
    }
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null){
            return head;
        }
        ListNode temp = head.next;
        ListNode newHead = reverse(head.next);
        temp.next = head;
        head.next = null;
        return newHead;
    }

    public void deal(ListNode head,int c){
        if(head==null){
            return;
        }
        int up=head.val/10;
        int mod=head.val%10;
        head.val=mod+c;
        if(head.next==null&&up!=0){
            head.next=new ListNode(0);
        }
        deal(head.next,up);

    }
}