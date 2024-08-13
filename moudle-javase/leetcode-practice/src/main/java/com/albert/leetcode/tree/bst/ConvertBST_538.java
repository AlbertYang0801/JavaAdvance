package com.albert.leetcode.tree.bst;

import com.albert.leetcode.entity.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * @author yjw
 * @date 2021/10/7 16:51
 */
public class ConvertBST_538 {

    Integer sum = 0;

    public TreeNode convertBST(TreeNode root) {
        helper(root);
        return root;
    }

    public void helper(TreeNode root){
        //base case
        if(root==null){
            return;
        }
        //中序遍历
        //先右边
        helper(root.right);
        sum=sum+root.val;
        root.val=sum;
        //再左边
        helper(root.left);
    }

}
