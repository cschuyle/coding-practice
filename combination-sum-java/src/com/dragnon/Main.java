package com.dragnon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{2, 3, 6, 7}, 7).toArray()));
        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{2, 3, 5}, 8).toArray()));
        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{2}, 1).toArray()));
        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{}, 0).toArray()));
        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{1}, 1).toArray()));
        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{2, 7, 6, 3, 5, 1}, 9).toArray()));
//        System.out.println("Solution " + Arrays.toString(solution.combinationSum(new int[]{0, 1}, 1).toArray()));
    }
}

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        doit(results, candidates, target, 0, new ArrayList<>(), 0);
        return results;
    }

    private void doit(List<List<Integer>> results, int[] candidates, int target, int candidateIndex, List<Integer> accumulator, int sum) {
        if (candidateIndex >= candidates.length) {
            return;
        }
        int candidate = candidates[candidateIndex];
        if (candidate == 0) {
            throw new IllegalArgumentException("Can't use 0");
        }
        doit(results, candidates, target, candidateIndex + 1, new ArrayList<>(accumulator), sum);
        while (sum + candidate <= target) {

            accumulator.add(candidate);
            sum += candidate;

            if (sum == target) {
                results.add(accumulator);
                break; //next candidate
            }
            doit(results, candidates, target, candidateIndex + 1, new ArrayList<>(accumulator), sum);
        }
    }
}
