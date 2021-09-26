package com.albert.leetcode.tree;


import com.albert.utils.jackson.JsonUtil;
import com.alibaba.fastjson.JSON;

/**
 * labuladong-二叉树练习题目
 *
 * @author yangjunwei
 * @date 2021/9/26 5:43 下午
 */
public class LabuladongBinaryTree {


    /**
     * 226-翻转二叉树
     * 核心思想：交换二叉树的左右子树
     */
    public TreeNode invertTree(TreeNode root) {
        //base case
        if (root == null) {
            return root;
        }
        //交换根节点的左右子树
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //左右子树继续翻转
        invertTree(root.left);
        invertTree(root.right);

        return root;
    }


    /**
     * 翻转二叉树测试
     */
    public void invertTreeTest() {
        //构建一个中序遍历 1 2 4 5 3 的二叉树。
        TreeNode two = new TreeNode();
        TreeNode three = new TreeNode();
        TreeNode four = new TreeNode();
        TreeNode five = new TreeNode();

        TreeNode one = new TreeNode();
        one.val = 1;
        one.left = two;
        one.right = three;

        two.val = 2;
        two.left = four;
        two.right = five;

        three.val = 3;
        four.val = 4;
        five.val = 5;

        System.out.println("翻转前："+ one.toString());
        TreeNode treeNode = invertTree(one);
        System.out.println("翻转后："+treeNode.toString());
    }


    public static void main(String[] args) {
        LabuladongBinaryTree labuladongBinaryTree = new LabuladongBinaryTree();
        labuladongBinaryTree.invertTreeTest();
    }


}
