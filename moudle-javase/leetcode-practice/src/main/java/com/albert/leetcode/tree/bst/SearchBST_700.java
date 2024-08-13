package com.albert.leetcode.tree.bst;

import com.albert.leetcode.entity.TreeNode;

/**
 * 700. 二叉搜索树中的搜索
 *
 * @author yangjunwei
 * @date 2021/10/8 1:40 下午
 */
public class SearchBST_700 {

    public TreeNode searchBST(TreeNode root, int val) {
        //base case
        if (root == null) {
            return null;
        }

        //匹配
        if (root.val == val) {
            return root;
        }

        //二分查找
        if (root.val < val) {
            //右
            return searchBST(root.right, val);
        }
        //左
        return searchBST(root.left, val);
    }


}
