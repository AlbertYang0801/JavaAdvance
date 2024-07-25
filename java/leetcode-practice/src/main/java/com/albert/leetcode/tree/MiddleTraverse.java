package com.albert.leetcode.tree;

import com.albert.leetcode.tree.bst.BinarySortTree;
import com.albert.utils.jackson.JsonUtil;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 中序遍历
 *
 * @author Albert
 * @date 2021/4/12 下午5:27
 */
public class MiddleTraverse {

    private static List<Integer> getMiddleTraverseResult(Tree tree) {
        List<Integer> list = Lists.newArrayList();
        fillTree(tree, list);
        return list;
    }

    private static void fillTree(Tree tree, List<Integer> list) {
        //先遍历左子树
        fillLeftNode(tree, list);
        //再获取根节点
        list.add(tree.getValue());
        //再遍历右子树
        fillRightNode(tree,list);
    }


    private static void fillLeftNode(Tree tree, List<Integer> list) {
        //左子树为空，返回
        if (tree.getLeftNode() == null) {
            return;
        }
        //左子树
        Tree leftNode = tree.getLeftNode();
        if (leftNode.getRightNode() == null&&leftNode.getLeftNode()==null) {
            list.add(leftNode.getValue());
            return;
        }
        fillTree(leftNode,list);
    }

    private static void fillRightNode(Tree tree, List<Integer> list) {
        //右子树为空，返回
        if (tree.getRightNode() == null) {
            return;
        }
        //右子树
        Tree rightNode = tree.getRightNode();
        if (rightNode.getRightNode() == null&&rightNode.getLeftNode()==null) {
            list.add(rightNode.getValue());
            return;
        }
        fillTree(rightNode,list);
    }

    public static void main(String[] args) {
        //获取一颗二叉平衡树
        Tree testBinarySortTree = BinarySortTree.getTestBinarySortTree();
        System.out.println("获取到的平衡二叉树为："+JsonUtil.toString(testBinarySortTree));
        List<Integer> middleTraverseResult = getMiddleTraverseResult(testBinarySortTree);
        System.out.println("中序遍历结果为："+middleTraverseResult);
    }

}
