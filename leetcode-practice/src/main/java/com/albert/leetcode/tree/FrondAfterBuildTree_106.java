package com.albert.leetcode.tree;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 参考链接：https://mp.weixin.qq.com/s/OlpaDhPDTJlQ5MJ8tsARlA
 * @author yangjunwei
 * @date 2021/9/28 11:48 上午
 */
public class FrondAfterBuildTree_106 {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    public TreeNode buildTree(int[] inorder, int inStart, int inEnd,
                              int[] postorder, int postStart, int postEnd) {
        //base case
        if (inStart > inEnd) {
            return null;
        }

        //确认根节点
        int rootValue = postorder[postEnd];
        TreeNode root = new TreeNode(rootValue);

        //确认root节点index位置
        int index = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                index = i;
                break;
            }
        }

        int leftsize = index - inStart;
        root.left = buildTree(inorder, inStart, index - 1,
                postorder, postStart, postStart + leftsize - 1);

        root.right = buildTree(inorder, index + 1, inEnd,
                postorder, postStart + leftsize, postEnd - 1);

        return root;
    }


}
