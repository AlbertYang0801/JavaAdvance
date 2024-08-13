package com.albert.leetcode.other;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yangjunwei
 * @date 2021/11/15 4:41 下午
 */
@Getter
@Setter
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }




}
