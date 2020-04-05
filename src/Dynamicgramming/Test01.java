package Dynamicgramming;

import java.util.Arrays;

/**
 * 求一个数组中不相邻数字的最大和
 * 例：[4,1,1,9,1]
 * 思路：
 * 如果数组的第i个数被选择，那么它前面的这个数就不能选择，那么此时最大和为：opt(i) = arr[i] + opt(i-2)
 * 如果数组的第i个数不被选择，那么此时的最大和为：opt(i) = opt(i-1)
 * opt(0) = arr[0]
 * opt(1) = max( arr[0],arr[1] )
 */
public class Test01 {
    public static void main(String[] args) {
        int[] arr = {4, 1, 1, 9, 1};
        System.out.println(recSolution(arr, arr.length - 1));

        int[] opt = solution(arr);
        System.out.println(Arrays.toString(opt));
    }

    /**
     * 递归求解
     *
     * @param arr
     * @param i   第i个位置的最优解
     * @return
     */
    public static int recSolution(int[] arr, int i) {
        if (i == 0) {
            return arr[0];
        }
        if (i == 1) {
            if (arr[0] > arr[1]) {
                return arr[0];
            } else {
                return arr[1];
            }
        }
        // 选择第i个数的情况
        int selectRes = arr[i] + recSolution(arr, i - 2);
        // 不选择第i个数的情况
        int unSelectRes = recSolution(arr, i - 1);

        // 比较选择和不选择的结果
        if (selectRes > unSelectRes) {
            return selectRes;
        }
        return unSelectRes;
    }

    /**
     * 非递归求解，从arr[0]开始计算opt，并把就给存起来，这样可以避免计算"重复子问题"
     * @param arr
     * @return
     */
    public static int[] solution(int[] arr){
        int[] opt = new int[arr.length];
        opt[0] = arr[0];
        if (arr[1] > arr[0]) {
            opt[1] = arr[1];
        }else {
            opt[1] = arr[0];
        }
        for (int i = 2; i < arr.length; i++) {
            int select = arr[i] + opt[i-2];
            int unSelect = opt[i-1];
            if (select > unSelect){
                opt[i] = select;
            }else {
                opt[i] = unSelect;
            }
        }
        return opt;
    }
}
