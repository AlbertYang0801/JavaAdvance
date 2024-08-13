package com.albert.leetcode.tree.bst;

import com.albert.leetcode.entity.TreeNode;

/**
 * 1038. 把二叉搜索树转换为累加树
 *
 * @author yjw
 * @date 2021/10/7 17:04
 */
public class BstToGst_1038 {

    int sum = 0;

    public TreeNode bstToGst(TreeNode root) {
        helper(root);
        return root;
    }

    public void helper(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }
        //右
        helper(root.right);
        sum += root.val;
        root.val = sum;
        //左
        helper(root.left);
    }


}
