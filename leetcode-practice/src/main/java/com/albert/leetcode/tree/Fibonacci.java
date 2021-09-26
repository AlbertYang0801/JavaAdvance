package com.albert.leetcode.tree;

/**
 * 509. 斐波那契数
 * F(0)=0，F(1)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 2，n ∈ N*）
 * 0、1、1、2、3、5、8、13、21、34、……
 *
 * @author yangjunwei
 * @date 2021/9/26 10:30 上午
 */
public class Fibonacci {

    /**
     * 暴力递归解法
     */
    public int fibForce(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        //递归计算前两个数的和
        return fibForce(n - 1) + fibForce(n - 2);
    }

    /**
     * 使用备忘录
     * 从递归树自上向下计算
     */
    public int fibBackup(int n) {
        //备忘录【保存已计算的结果】
        int[] nums = new int[n + 1];
        return helper(nums, n);
    }

    private int helper(int[] nums, int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        //判断数组【备忘录】是否已存在数据
        if (nums[n] != 0) {
            return nums[n];
        }
        nums[n] = helper(nums, n - 1) + helper(nums, n - 2);
        return nums[n];
    }

    /**
     * 使用 DP-table(动态规划表) 自下向上计算
     */
    public int fib(int n) {
        //DP-table
        if (n == 0 || n == 1) {
            return n;
        }
        int[] nums = new int[n + 1];
        nums[1] = 1;
        for (int i = 2; i <= n; i++) {
            nums[i] = nums[i - 1] + nums[i - 2];
        }
        return nums[n];
    }

    public int fibOptimization(int n) {
        //DP-table优化（节省数组空间）
        int pre=0;
        int curr=1;
        int sum=0;
        if (n == 0 || n == 1) {
            return n;
        }
        for (int i = 2; i <= n; i++) {
            sum = pre+curr;
            pre=curr;
            curr=sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println("暴力解法:" + fibonacci.fibForce(5));
        System.out.println("备忘录解法:" + fibonacci.fibBackup(5));
        System.out.println("DP-table解法:" + fibonacci.fib(5));
        System.out.println("DP-table优化解法:" + fibonacci.fibOptimization(5));
    }


}


//        斐波那契数，通常用F(n) 表示，形成的序列称为 斐波那契数列 。该数列由 0 和 1 开始，后面的每一项数字都是前面两项数字的和。也就是：
//
//        F(0) = 0，F(1)= 1
//        F(n) = F(n - 1) + F(n - 2)，其中 n > 1
//        给你 n ，请计算 F(n) 。
