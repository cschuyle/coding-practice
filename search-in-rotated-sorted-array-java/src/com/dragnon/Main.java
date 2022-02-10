package com.dragnon;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(new Solution().search(new int[]{1}, 1)); // 0
        System.out.println(solution.search(new int[]{5, 6, 4}, 5)); // 0
        System.out.println(solution.search(new int[]{5, 1, 3}, 0)); // 0
        System.out.println(new Solution().search(new int[]{1}, 42)); // -1
        System.out.println(solution.search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0)); // 4
        System.out.println(solution.search(new int[]{8, 9, 0, 1, 2, 3, 4, 5, 6, 7}, 9)); // 1
        System.out.println(solution.search(new int[]{6, 0, 1, 2, 3, 4, 5}, 0)); // 1
    }
}

class Solution {
    public int search(int[] nums, int target) {
        int beginIndex = 0, endIndex = nums.length - 1;

        do {
            int centerIndex = beginIndex + (endIndex - beginIndex) / 2;
            int end = nums[endIndex], begin = nums[beginIndex], center = nums[centerIndex];
//            System.out.println(String.format("begin index %d center index %d end index %d | begin %d center %d end %d", beginIndex, centerIndex, endIndex, begin, center, end));
            if (target == center) {
                return centerIndex;
            }
            if (beginIndex == endIndex) {
                return -1;
            }
            if (begin <= center) {
                if (begin <= target && target < center) {
                    endIndex = centerIndex - 1;
                } else {
                    beginIndex = centerIndex + 1;
                }
            } else if (end >= center) {
                if (end >= target && target > center) {
                    beginIndex = centerIndex + 1;
                } else {
                    endIndex = centerIndex - 1;
                }
            } else {
                return -1;
            }
        } while (true);
    }
}

// TODO Another solution: First locate the pivot item by binary search.
//      Then do the binary search adjusted by the pivot index to find the element.
