package com.albert.leetcode.tree.bst;

import com.albert.leetcode.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 230. 二叉搜索树中第K小的元素
 * 二叉搜索树的中序遍历结果是升序的
 *
 * @author yjw
 * @date 2021/10/7 16:36
 */
public class kthSmallest_230 {

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        helper(root, list);
        return list.get(k-1);
    }

    public void helper(TreeNode root, List<Integer> list) {
        //base case
        if (root == null) {
            return;
        }
        helper(root.left, list);
        //最后添加元素
        list.add(root.val);
        helper(root.right, list);
    }


}
