package com.dragnon;

public class Main {

    public static void main(String[] args) {

        Solution[] solutions = {new NaiveSolution(), new FastSolution()};
        for (Solution solution : solutions) {
            System.out.println(solution.maxSubArray(new int[]{}));
            System.out.println(solution.maxSubArray(new int[]{1, 0})); // 1
            System.out.println(solution.maxSubArray(new int[]{42})); // 42
            System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
        }
    }
}

interface Solution {
    int maxSubArray(int[] nums);
}

class FastSolution implements Solution {

    @Override
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int globalMax = nums[0], localMax = nums[0];
        for (int i = 1; i < nums.length; i++) {
            localMax = Math.max(nums[i], localMax + nums[i]);
            globalMax = Math.max(localMax, globalMax);
        }
        return globalMax;
    }
}

class NaiveSolution implements Solution {

    @Override
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < nums.length; ++j) {
                int thisSum = 0;
                for (int k = i; k <= j; ++k) {
                    thisSum += nums[k];
                }
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }
}
