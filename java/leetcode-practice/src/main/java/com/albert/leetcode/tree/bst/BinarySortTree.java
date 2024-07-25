package com.albert.leetcode.tree.bst;


import com.albert.leetcode.tree.Tree;
import com.albert.utils.jackson.JsonUtil;

/**
 * 二叉排序树的实现
 * @author Albert
 * @date 2021/4/12 下午5:29
 */
public class BinarySortTree {

    /**
     * 向指定树添加元素
     * @param value 元素
     * @param tree 树
     */
    private static void insert(int value, Tree tree){
        if(tree.getValue()==0){
            tree.setValue(value);
        }else{
            if(tree.getValue()>value){
                //放到左节点
                if(tree.getLeftNode()==null){
                    Tree leftTree = new Tree();
                    leftTree.setValue(value);
                    tree.setLeftNode(leftTree);
                }else{
                    insert(value,tree.getLeftNode());
                }
            }else{
                if(tree.getRightNode()==null){
                    Tree rightTree = new Tree();
                    rightTree.setValue(value);
                    tree.setRightNode(rightTree);
                }else{
                    insert(value,tree.getRightNode());
                }
            }
        }
    }

    /**
     * 获取测试用的二叉平衡树
     * @return
     */
    public static Tree getTestBinarySortTree(){
        Tree tree = new Tree();
        insert(10,tree);
        insert(5,tree);
        insert(15,tree);
        insert(20,tree);
        insert(16,tree);
        insert(3,tree);
        insert(7,tree);
        return tree;
    }

    public static void main(String[] args) {
        Tree testBinarySortTree = getTestBinarySortTree();
        System.out.println(JsonUtil.toString(testBinarySortTree));
    }



}
