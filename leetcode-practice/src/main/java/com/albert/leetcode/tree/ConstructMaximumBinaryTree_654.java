package com.albert.leetcode.tree;

/**
 * 654. 最大二叉树
 *
 * @author yangjunwei
 * @date 2021/9/27 3:32 下午
 */
public class ConstructMaximumBinaryTree_654 {

    /**
     * 利用中序遍历和递归
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        //base case
        if (nums.length <= 0) {
            return null;
        }
        if (nums.length == 1) {
            return new TreeNode(nums[0]);
        }

        //1.找出最大值
        int index = 0;
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                index = i;
                max = nums[i];
            }
        }
        //确定根节点
        TreeNode treeNode =new TreeNode(max);

        //2.找到最大值左边的数组，递归
        int[] leftNums = new int[index];
        for (int i = 0; i < index; i++) {
            leftNums[i] = nums[i];
        }
        TreeNode leftNode = constructMaximumBinaryTree(leftNums);


        //3.找到最大值右边的数组，递归
        int[] rightNums = new int[nums.length - index - 1];
        for (int i = index + 1; i < nums.length; i++) {
            rightNums[i-index-1] = nums[i];
        }
        TreeNode rightNode = constructMaximumBinaryTree(rightNums);

        treeNode.left=leftNode;
        treeNode.right=rightNode;
        return treeNode;
    }


}


//        给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
//
//        二叉树的根是数组 nums 中的最大元素。
//        左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
//        右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
//        返回有给定数组 nums 构建的 最大二叉树 。
//
//        来源：力扣（LeetCode）
//        链接：https://leetcode-cn.com/problems/maximum-binary-tree
//        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。