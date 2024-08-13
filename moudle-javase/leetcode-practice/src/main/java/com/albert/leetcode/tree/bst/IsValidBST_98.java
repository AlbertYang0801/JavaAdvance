package com.albert.leetcode.tree.bst;

import com.albert.leetcode.entity.TreeNode;

/**
 * 98. 验证二叉搜索树
 * 按照中序遍历升序的特点，若根节点value<=left，则不满足BST
 * @author yangjunwei
 * @date 2021/10/8 11:40 上午
 */
public class IsValidBST_98 {

    //当前node的前一个value值
    long prev = Long.MIN_VALUE;

    /**
     * 每一个二叉树需要做的事情
     * 1.base case;树为null为BST
     * 2.校验左子树是否为BST
     * 3.判断根节点root的value是否小于prev
     * 4.校验右子树是否为BST
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        //base case
        if (root == null) {
            return true;
        }
        //左
        if (!isValidBST(root.left)) {
            return false;
        }
        //根
        //若根节点value<=left
        if (root.val <= prev) {
            return false;
        }
        prev=root.val;
        //右
        return isValidBST(root.right);
    }

    public static void main(String[] args) {
        Long value = Long.MIN_VALUE;
        System.out.println(value);
        int minValue = Integer.MIN_VALUE;
        System.out.println(minValue);
    }

}
