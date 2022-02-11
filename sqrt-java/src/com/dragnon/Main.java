package com.dragnon;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Solution " + solution.mySqrt(0));
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
        if (x == 0 || x == 1) {
            return x;
        }
        int begin = 1, end = x/2;
        while (begin <= end) {
            int center = begin + (end - begin) / 2;
            long squared = (long) center * center;
            if (squared == x) {
                return center;
            }
            if (squared < x) {
                begin = center + 1;
            } else {
                end = center - 1;
            }
        }
        if (end * end < x) {
            return end;
        }
        return begin;
    }
}
