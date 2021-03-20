package com.albert.spring.tree;

import com.albert.utils.jackson.JsonUtil;

/**
 * @author Albert
 * @date 2021/3/17 下午9:40
 */
public class TreeTest {


    private void insert(int value,Tree tree){
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



    public static void main(String[] args) {
        TreeTest treeTest = new TreeTest();
        Tree tree = new Tree();
        treeTest.insert(4,tree);
        treeTest.insert(1,tree);
        treeTest.insert(2,tree);
        treeTest.insert(3,tree);
        System.out.println(JsonUtil.toString(tree));


    }




}
