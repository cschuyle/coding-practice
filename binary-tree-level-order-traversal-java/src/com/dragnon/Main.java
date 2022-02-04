package com.dragnon;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
}

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        if (root == null) {
            return output;
        }
        List<TreeNode> currentLevel = List.of(root);

        while (currentLevel.size() > 0) {
            output.add(extractValues(currentLevel));
            currentLevel = collectChildren(currentLevel);
        }
        return output;
    }

    private List<TreeNode> collectChildren(List<TreeNode> fromLevel) {
        List<TreeNode> nextLevel = new ArrayList<>();
        for (TreeNode node : fromLevel) {
            if (node.left != null) {
                nextLevel.add(node.left);
            }
            if (node.right != null) {
                nextLevel.add(node.right);
            }
        }
        return nextLevel;
    }

    private List<Integer> extractValues(List<TreeNode> level) {
        return level.stream().map(node -> node.val).collect(Collectors.toList());
    }
}
