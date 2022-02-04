package com.dragnon;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(
                new Solution().fullJustify(new String[]{"What", "must", "be", "acknowledgment", "shall", "be"}, 16).toString());
        System.out.println(
                new Solution().fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16).toString());
    }
}

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> output = new ArrayList<>();

        List<String> acc = new ArrayList<>();
        int accTotalChars = 0;
        int wordIndex = 0;
        while (wordIndex < words.length) {
            String currentWord = words[wordIndex];
            // Take into account spaces between words to see if there is still space
            if (accTotalChars + acc.size() + currentWord.length() <= maxWidth) {
                acc.add(currentWord);
                accTotalChars += currentWord.length();
                wordIndex++;
            } else {
                output.add(outputNonLastLine(acc, maxWidth));
                acc = new ArrayList<>();
                accTotalChars = 0;
            }
        }
        if (accTotalChars > 0) {
            output.add(outputLastLine(acc, maxWidth));
        }
        return output;
    }

    private String outputLastLine(List<String> words, int maxWidth) {
        var line = new StringBuilder();
        int i = 0;
        for (String word : words) {
            line.append(word);
            if (i == words.size() - 1) {
                appendSpaces(line, maxWidth - line.length());
                return line.toString();
            }
            line.append(' ');
            i++;
        }
        return line.toString();
    }

    private String outputNonLastLine(List<String> words, int maxWidth) {
        if (words.size() == 1) {
            return outputLastLine(words, maxWidth);
        }
        var line = new StringBuilder();

        // Compute total word length
        int wordLengthSum = words.stream().mapToInt(String::length).sum();

        int numWords = words.size();
        int totalSpaces = maxWidth - wordLengthSum;
        int[] spaceCounts = new int[numWords - 1];
        for (int i = 0; i < totalSpaces; ++i) {
            spaceCounts[i % (numWords - 1)] += 1;
        }

        int i = 0;
        for (String word : words) {
            line.append(word);
            if (i == numWords - 1) {
                break;
            }
            appendSpaces(line, spaceCounts[i]);
            i++;
        }
        return line.toString();
    }

    private void appendSpaces(StringBuilder line, int padChars) {
        for (int chars = 0; chars < padChars; chars++) {
            line.append(' ');
        }
    }
}
