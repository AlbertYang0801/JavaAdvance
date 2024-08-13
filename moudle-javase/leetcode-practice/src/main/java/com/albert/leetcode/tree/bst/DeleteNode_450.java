package com.albert.leetcode.tree.bst;

import com.albert.leetcode.entity.TreeNode;

/**
 * 450. 删除二叉搜索树中的节点
 *
 * @author yangjunwei
 * @date 2021/10/8 2:01 下午
 */
public class DeleteNode_450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        //base case
        if (root == null) {
            return null;
        }
        //是否等于删除的value
        if (root.val == key) {
            //三种情况
            //1.删除的node只有根节点
            if (root.right == null && root.left == null) {
                return null;
            }
            //2.删除的node只有左子树或右子树
            if (root.right == null) {
                return root.left;
            }

            if (root.left == null) {
                return root.right;
            }

            //3.删除的node是完整的树
            //右子树right作为根节点，原左子树left插入到右子树right的左节点，保证BST的有序性。
            return helperInsert(root.right, root.left);
        }
        //二分查找
        //大于的话，查找右子树。
        if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            //小于的话，查找左子树。
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    public TreeNode helperInsert(TreeNode right, TreeNode left) {
        //右子树无左节点
        if (right.left == null) {
            right.left = left;
            return right;
        }
        //递归插入
        right.left = helperInsert(right.left, left);
        return right;
    }


}
