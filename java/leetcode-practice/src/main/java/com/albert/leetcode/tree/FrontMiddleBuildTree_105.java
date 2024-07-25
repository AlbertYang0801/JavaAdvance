package com.albert.leetcode.tree;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 参考链接：https://mp.weixin.qq.com/s/OlpaDhPDTJlQ5MJ8tsARlA
 * @author yangjunwei
 * @date 2021/9/28 10:09 上午
 */
public class FrontMiddleBuildTree_105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd,
                          int[] inorder, int inStart, int inEnd) {

        //base case
        if (preStart > preEnd) {
            return null;
        }

        int rootValue = preorder[preStart];
        //根据前序遍历获取root节点
        TreeNode root = new TreeNode(preorder[preStart]);

        //获取root节点的index
        int index = -1;
        //确认根节点位置（关键）
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootValue) {
                index = i;
                break;
            }
        }

        int leftSize = index - inStart;
        //递归左子树
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, index - 1);
        //递归右子树
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                inorder, index + 1, inEnd);

        return root;
    }


}
