package com.dragnon;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.dragnon.Color.*;

public class Main {

    public static void main(String[] args) {
        Solution[] solutions = {new RecursiveMemoizationSolution()};
        for (Solution solution : solutions) {
            System.out.println("Solution is " +
                    solution.minCost(new int[][]{new int[]{17, 2, 17}, new int[]{16, 16, 5}, new int[]{14, 3, 19}})); // 10
            System.out.println("Solution is " +
                    solution.minCost(new int[][]{new int[]{1, 2, 3}, new int[]{2, 10, 20}, new int[]{5, 6, 100}})); // 2+2+6 = 10
        }
    }
}

enum Color {
    RED(0),
    BLUE(1),
    GREEN(2);

    Color(int value) {
        this.value = value;
    }

    public final int value;
}

class Key {
    private final int level;
    private final Color color;

    public Key(int level, Color color) {
        this.level = level;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return level == key.level && color == key.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, color);
    }
}

interface Solution {
    int minCost(int[][] costs);
}

class RecursiveMemoizationSolution implements Solution {

    private int[][] costs;
    private final Map<Key, Integer> partialSolutions = new HashMap<>();

    @Override
    public int minCost(int[][] costs) {
        this.costs = costs;
        if (costs.length == 0) {
            return 0;
        }
        return Math.min(levelColorCost(0, RED), Math.min(levelColorCost(0, GREEN), levelColorCost(0, BLUE)));
    }

    private int levelColorCost(int level, Color color) {
        Key key = new Key(level, color);
        if (partialSolutions.containsKey(key)) {
            return partialSolutions.get(key);
        }
        int partialSolution = costs[level][color.value];
        if (level == costs.length - 1) {
            return partialSolution;
        }
        partialSolution += switch (color) {
            case RED -> Math.min(levelColorCost(level + 1, GREEN), levelColorCost(level + 1, BLUE));
            case GREEN -> Math.min(levelColorCost(level + 1, RED), levelColorCost(level + 1, BLUE));
            case BLUE -> Math.min(levelColorCost(level + 1, RED), levelColorCost(level + 1, GREEN));
        };
        partialSolutions.put(key, partialSolution);
        return partialSolution;
    }
}
