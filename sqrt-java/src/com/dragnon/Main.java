package com.dragnon;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Solution " + solution.mySqrt(1));
        System.out.println("Solution " + solution.mySqrt(2));
        System.out.println("Solution " + solution.mySqrt(3));
        System.out.println("Solution " + solution.mySqrt(4));
        System.out.println("Solution " + solution.mySqrt(6));
        System.out.println("Solution " + solution.mySqrt(8));
        System.out.println("Solution " + solution.mySqrt(9));
        System.out.println("Solution " + solution.mySqrt(Integer.MAX_VALUE));
    }
}


class Solution {
    public int mySqrt(int x) {
        if (x < 2) {
            return 1;
        }
        int begin = 2, end = x / 2;
        while (end - begin > 1) {
            int center = begin + (end - begin) / 2;
            long squared = (long) center * center;
            if (squared == x) {
                return x;
            }
            if (squared < x) {
                begin = center + 1;
            } else {
                end = center - 1;
            }
        }
        return begin;
    }
}
