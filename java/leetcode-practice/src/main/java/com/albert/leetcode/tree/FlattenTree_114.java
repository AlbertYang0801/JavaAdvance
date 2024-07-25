package com.albert.leetcode.tree;

/**
 * 114. 二叉树展开为链表
 *
 * @author yangjunwei
 * @date 2021/9/27 11:37 上午
 */
public class FlattenTree_114 {

    public void flatten(TreeNode root) {
        //base case
        if (root == null) {
            return;
        }

        //1.展开左子树
        flatten(root.left);
        //2.展开右子树
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;
        //3.左子树链表放到右子树
        root.left=null;
        root.right=left;

        TreeNode node = root;
        //4.展开的右子树追加到现在右子树上
        while (node.right != null) {
            node = node.right;
        }
        node.right = right;


    }


}


//        展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
//        展开后的单链表应该与二叉树 先序遍历 顺序相同。
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。