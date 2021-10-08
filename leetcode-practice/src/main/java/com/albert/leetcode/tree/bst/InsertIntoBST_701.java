package com.albert.leetcode.tree.bst;

import com.albert.leetcode.tree.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 *
 * @author yangjunwei
 * @date 2021/10/8 1:47 下午
 */
public class InsertIntoBST_701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        //base case
        if (root == null) {
            TreeNode treeNode = new TreeNode();
            treeNode.val = val;
            return treeNode;
        }
        //二分查找
        if (root.val < val) {
            //继续查找
            root.right = insertIntoBST(root.right, val);
        }else{
            //左
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }


}
