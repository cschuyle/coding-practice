package com.dragnon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        twosum(arr, 22);
        twosum_efficient(arr, 22);
        twosum_space_efficient(arr, 22);
    }

    static void twosum(int[] arr, int target) {
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    outputAnswer(arr, i, j);
                    return;
                }
            }
        }
    }

    private static void outputAnswer(int[] arr, int i, int j) {
        System.out.println("Input " + Arrays.toString(arr) + ", output: [" + i + ", " + j + "]");
    }

    /*
    O(n):
    Build a hash table mapping values to indices.
    For each value, see if the value that would sum to target exists.
     */
    static void twosum_efficient(int[] arr, int target) {
        var value_index = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; ++i) {
            value_index.put(arr[i], i);
        }
        for (Map.Entry<Integer, Integer> entry : value_index.entrySet()) {
            int thisValue = entry.getKey();
            int otherValue = target - thisValue;
            if (value_index.containsKey(target - thisValue)) {
                int thisIndex = entry.getValue();
                int otherIndex = value_index.get(otherValue);
                outputAnswer(arr, thisIndex, otherIndex);
                return;
            }
        }
    }
    static short[] value_index = new short[1000000000];
    static short NO_VALUE = -1;
    static {
        for (int i = 0; i < value_index.length; ++i) {
            value_index[i] = NO_VALUE;
        }

    }
    static void twosum_space_efficient(int[] arr, int target) {

        for (int i = 0; i < arr.length; ++i) {
            value_index[arr[i]] = (short)i;
        }
        for (int i = 0; i < value_index.length; ++i) {
            if (value_index[i] != NO_VALUE && value_index[target - i] != NO_VALUE) {
                outputAnswer(arr, value_index[i], value_index[target - i]);
                return;
            }
        }
    }
}
