package com.dragnon;

import java.util.Deque;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Solution: " + new Solution().isValid("()[]{}"));
        System.out.println("Solution: " + new Solution().isValid("(]"));
        System.out.println("Solution: " + new Solution().isValid("([)]"));
        System.out.println("Solution: " + new Solution().isValid("]"));
        System.out.println("Solution: " + new Solution().isValid("<[]>[()]({}){<>}"));
    }
}

enum Validation {
    Valid, Invalid
}

class Solution {

    enum BracketShape {
        Paren, Square, Curly, Chevron
    }

    Deque<BracketShape> stack = new LinkedList<>();

    public boolean isValid(String s) {
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(' -> { if (validateBegin(BracketShape.Paren) == Validation.Invalid) return false; }
                case ')' -> { if (endIsInvalid(BracketShape.Paren) == Validation.Invalid) return false; }
                case '[' -> { if (validateBegin(BracketShape.Square) == Validation.Invalid) return false; }
                case ']' -> { if (endIsInvalid(BracketShape.Square) == Validation.Invalid) return false; }
                case '{' -> { if (validateBegin(BracketShape.Curly) == Validation.Invalid) return false; }
                case '}' -> { if (endIsInvalid(BracketShape.Curly) == Validation.Invalid) return false; }
                case '<' -> { if (validateBegin(BracketShape.Chevron) == Validation.Invalid) return false; }
                case '>' -> { if (endIsInvalid(BracketShape.Chevron) == Validation.Invalid) return false; }
            }
        }
        return stack.size() == 0;
    }

    private Validation validateBegin(BracketShape p) {
        stack.push(p);
        return Validation.Valid;
    }

    private Validation endIsInvalid(BracketShape p) {
        if (stack.size() == 0) {
            return Validation.Invalid;
        }
        if (stack.peek() == p) {
            stack.pop();
            return Validation.Valid;
        }
        return Validation.Invalid;
    }
}
