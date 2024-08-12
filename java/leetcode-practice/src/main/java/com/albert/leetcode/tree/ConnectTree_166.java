package com.albert.leetcode.tree;

import cn.hutool.json.JSONUtil;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 *
 * @author yangjunwei
 * @date 2021/9/27 10:39 上午
 */
public class ConnectTree_166 {

    public static Node connect(Node root) {
        //base case
        if (root == null) {
            return null;
        }
        helperConnect(root.left, root.right);
        return root;
    }

    /**
     * 递归
     * 1. 同个根节点连结
     * 2. 相邻根节点的节点连结
     */
    public static void helperConnect(Node one, Node two) {
        //base case
        if (one == null || two == null) {
            return;
        }
        //node.left.next=node.right
        one.next = two;

        //连结同一个根节点的左右节点
        helperConnect(one.left, one.right);
        helperConnect(two.left, two.right);

        //连结相邻根节点的左右节点
        helperConnect(one.right, two.left);
    }

    public static void main(String[] args) {
        Node one = new Node();
        Node two = new Node();
        Node three = new Node();
        Node four = new Node();
        Node five = new Node();
        Node six = new Node();

        one.left = two;
        one.right = three;
        one.val = 1;

        two.left = four;
        two.right = five;
        two.val = 2;

        three.left = six;
        three.right = null;
        three.val = 3;

        four.val = 4;
        five.val = 5;
        six.val = 6;

        Node root = ConnectTree_166.connect(one);
        System.out.println(JSONUtil.toJsonStr(root));
    }

    //---------------------------------------------------------------------------------------

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
            this.left = null;
            this.right = null;
            this.next = null;
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    ", next=" + next +
                    '}';
        }
    }


}
