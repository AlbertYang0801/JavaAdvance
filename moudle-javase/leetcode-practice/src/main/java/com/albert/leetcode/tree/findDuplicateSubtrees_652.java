package com.albert.leetcode.tree;

import com.albert.leetcode.entity.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 652. 寻找重复的子树
 * 参考链接：https://mp.weixin.qq.com/s/LJbpo49qppIeRs-FbgjsSQ
 *
 * @author yjw
 * @date 2021/9/29 22:08
 */
public class findDuplicateSubtrees_652 {


    //key：树的结果集；value：相同结果集个数
    HashMap<String, Integer> map = new HashMap<>();
    //存放重复的子树
    LinkedList<TreeNode> resultList = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return resultList;
    }


    public String helper(TreeNode root) {
        //base case
        if (root == null) {
            return "#";
        }

        //后序遍历树
        String leftResult = helper(root.left);
        String rightResult = helper(root.right);
        //1.找到以自己为根的树结果集
        String result = leftResult + "," + rightResult + "," + root.val;


        int countValue = 0;

        //2.判断自己的结果集是否重复
        if (map.containsKey(result)) {
            countValue=map.get(result);
            //第二次添加，记录结果值
            if(countValue==1){
                resultList.add(root);
            }
        }
        map.put(result,countValue+1);
            //首次记录结果集

        return result;
    }


}
