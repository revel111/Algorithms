package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Stacks {
    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }

    // Problem 20
    // https://leetcode.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        Map<Character, Character> dict = Map.of(')', '(', ']', '[', '}', '{');
        Stack<Character> stack = new Stack<>();

        for (char paran : s.toCharArray()) {
            if (dict.containsKey(paran)) {
                if (!stack.isEmpty() && Objects.equals(dict.get(paran), stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(paran);
            }
        }

        return stack.isEmpty();
    }

    // Problem 155
    // https://leetcode.com/problems/min-stack/
    // Space: O(n) extra
    // Time: O(1) for each operation
    private static class MinStack {

        private final Stack<Integer> stack = new Stack<>();
        private final Stack<Integer> minStack = new Stack<>();

        public MinStack() {

        }

        public void push(int val) {
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                int currMin = this.getMin();
                minStack.push(Math.min(currMin, val));
            }
            stack.push(val);
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
                minStack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    // Problem 155
    // https://leetcode.com/problems/min-stack/
    // this version is better than the version above
    // Space: O(1)
    // Time: O(1) for each operation
    private static class MinStackBetter {

        private final Stack<Long> stack = new Stack<>();
        private long min;

        public MinStackBetter() {

        }

        public void push(int val) {
            if (stack.isEmpty()) {
                min = val;
                stack.push(0L);
            } else {
                stack.push(val - min);
                min = Math.min(val, min);
            }
        }

        public void pop() {
            if (!stack.isEmpty()) {
                long pop = stack.pop();

                if (pop < 0) {
                    min = min - pop;
                }
            }
        }

        public int top() {
            long top = stack.peek();

            if (top < 0) {
                return (int) min;
            } else {
                return (int) (min + top);
            }
        }

        public int getMin() {
            return (int) min;
        }
    }

    // Problem 150
    // https://leetcode.com/problems/evaluate-reverse-polish-notation/
    public static int evalRPN(String[] tokens) {
        Deque<Integer> numbers = new ArrayDeque<>();

        for (String token : tokens) {
            if (!Objects.equals(token, "+") && !Objects.equals(token, "-") && !Objects.equals(token, "*") && !Objects.equals(token, "/")) {
                numbers.push(Integer.parseInt(token));
            } else {
                int right = numbers.pop(), left = numbers.pop();

                int answer = switch (token) {
                    case "+" -> left + right;
                    case "-" -> left - right;
                    case "*" -> left * right;
                    default -> left / right;
                };
                numbers.push(answer);
            }
        }

        return numbers.pop();
    }

    // Problem 739
    // https://leetcode.com/problems/daily-temperatures/
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

        return answer;
    }
}
