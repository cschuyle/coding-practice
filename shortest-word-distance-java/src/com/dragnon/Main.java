package com.dragnon;

public class Main {

    public static void main(String[] args) {
        System.out.println("Solution is " + new Solution().shortestDistance(new String[]{"practice", "makes", "perfect", "coding", "makes"},
                "coding", "practice"));
    }
}

class Solution {
    public int shortestDistance(String[] wordsDict, String word1, String word2) {
        int index1 = -1, index2 = -1, minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < wordsDict.length; ++i) {
            String currentWord = wordsDict[i];
            if (currentWord.equals(word1)) {
                index1 = i;
            } else if (currentWord.equals(word2)) {
                index2 = i;
            } else {
                continue;
            }
            if (index1 == -1 || index2 == -1) {
                continue;
            }
            int distance = Math.abs(index1 - index2);
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }
}
