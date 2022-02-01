package com.dragnon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int target = 22;
        run(new NaiveTwoSum(), arr, target);
        run(new EfficientTwoSum(), arr, target);
        run(new SpaceEfficientTwoSum(), arr, target);
    }

    private static void run(TwoSum twoSum, int[] arr, int target) {
        var solution = twoSum.apply(arr, target);
        if (solution != null) {
            System.out.println(twoSum.getClass().getSimpleName() + " solution to " + Arrays.toString(arr) + " is " + Arrays.toString(solution));
        } else {
            System.out.println(twoSum.getClass().getSimpleName() + " solution to " + Arrays.toString(arr) + " does not exist");
        }
    }
}

interface TwoSum {
    int[] apply(int[] input, int target);
}

class NaiveTwoSum implements TwoSum {
    @Override
    public int[] apply(int[] input, int target) {
        for (int i = 0; i < input.length; ++i) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}

// TODO: The next 2 don't handle the case where a value appears more than once in the input !!

class EfficientTwoSum implements TwoSum {

    @Override
    public int[] apply(int[] input, int target) {

    /*
    O(n):
    Build a hash table mapping values to indices.
    For each value, see if the value that would sum to target exists.
     */
        var value_index = new HashMap<Integer, Integer>();
        for (int i = 0; i < input.length; ++i) {
            value_index.put(input[i], i);
        }
        for (Map.Entry<Integer, Integer> entry : value_index.entrySet()) {
            int thisValue = entry.getKey();
            int otherValue = target - thisValue;
            if (value_index.containsKey(target - thisValue)) {
                int thisIndex = entry.getValue();
                int otherIndex = value_index.get(otherValue);
                return new int[]{thisIndex, otherIndex};
            }
        }
        return null;
    }
}

class SpaceEfficientTwoSum implements TwoSum {

    short[] value_index = new short[1000000000];
    short NO_VALUE = -1;

    public SpaceEfficientTwoSum() {
        Arrays.fill(value_index, NO_VALUE);
    }

    @Override
    public int[] apply(int[] input, int target) {
        for (int i = 0; i < input.length; ++i) {
            value_index[input[i]] = (short) i;
        }
        for (int i = 0; i < value_index.length; ++i) {
            if (value_index[i] != NO_VALUE && value_index[target - i] != NO_VALUE) {
                return new int[]{value_index[i], value_index[target - i]};
            }
        }
        return null;
    }
}

