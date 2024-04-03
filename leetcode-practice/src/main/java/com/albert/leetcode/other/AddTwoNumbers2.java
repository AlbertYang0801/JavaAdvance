package com.albert.leetcode.other;

/**
 * @author yangjunwei
 * @date 2021/11/15 4:41 下午
 */
public class AddTwoNumbers2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //链表
        ListNode res = new ListNode();
        //指针
        ListNode curr = res;
        //进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            int value1 = l1 == null ? 0 : l1.val;
            int value2 = l2 == null ? 0 : l2.val;
            int sum = value1 + value2 + carry;
            //进位
            carry = sum / 10;
            sum %= 10;
            curr.next = new ListNode(sum);
            //指针指向下个节点
            curr = curr.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        //最后如果还有进位，增加一个节点
        if(carry==1){
            curr.next = new ListNode(1);
        }
        return res.next;
    }
    //从头开始相加，大于10的话进1位，最后一位若为空，如果有进位，则填充1。


}
