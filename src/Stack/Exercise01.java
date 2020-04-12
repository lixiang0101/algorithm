package Stack;

import java.util.Stack;

/**
 * 剑指offer：输入两个整数序列，第一个序列表示栈的压入序列，判断第二个序列是不是该栈的弹出序列
 */

public class Exercise01 {
    public static void main(String[] args) {
        int[] input = {1, 2, 3, 4, 5};
        int[] output = {4, 3, 5, 1, 2};
        boolean solution = solution(input, output);
        System.out.println(solution);
    }

    public static boolean solution(int[] input, int[] output) {
        Stack<Integer> ints = new Stack<>();//辅助栈
        int i = 0;
        int j = 0;
        while (i < input.length && j < output.length) {
            ints.push(input[i]);//把input先压入栈
            i++;
            // 没压入一个数都比较一下栈顶的元素是不是跟output当前位置的元素一样，
            // 如果一样就出栈，output继续往下移动一位，继续比较
            while (!ints.isEmpty() && ints.peek() == output[j]) {
                ints.pop();
                j++;
            }
        }
        // 如果辅助栈为空，那么output就是弹出顺序，否则就不是
        if (ints.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
