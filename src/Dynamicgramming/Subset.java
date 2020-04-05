package Dynamicgramming;

import java.util.Arrays;

/**
 * 给定一个数据集和一个目标数s，判断是否存在有子数据集，这个子数据集的和等于s
 * {3,34,4,12,5,2} s=9
 * 思路：
 * 对与当前的这个数，要么选要么不选
 * 选：existSubset(arr[i-1],s-arr[i])
 * 不选：existSubset(arr[i-1])
 * 只要两种情况其中之一返回true，那么就存在
 * 递归基：
 * 1)、遍历到某个元素结束：s-arr[i] == 0
 * 2)、已经把所以元素都遍历完：i == 0 && s != arr[i]
 * <p>
 * 非递归方法：
 * 创建一个二维数组(subset)保存中间结果
 * 在上面递归的过程中，s是不断变化的，s变化范围是0～s，把s的范围作为列
 * 上面的递归过程是从数组的一头往另一头遍历的，所以把数组的每个数作为行
 * 比如 s=4，数据集arr：{2,1,1,2}
 * <p>
 *     0 1 2 3 4
 * 2|0 T F T F F
 * 1|1 T
 * 1|2 T
 * 2|3 T
 * 根据上面的递归基可以初始化二维数组的第一行和第一列
 * 1、s == 0，即第一列都为true
 * 2、i == 0 && s != arr[i] 即第一行除了 s == arr[i] 外都为false
 * 接着就是更新这个数组
 * 以行(用i表示)为单位更新，从第2行开始，就是从数组的第2个数开始
 * 如果当前这个数arr[i]大于s，那么subset[i][s] = subset[i-1][s]
 * 否则：分为选择当前值和不选当前值的情况：
 * select = subset[i-1][s-arr[i]]  就是上面的existSubset(arr[i-1],s-arr[i])
 * unSelect = subset[i-1][s]  就是上面的：existSubset(arr[i-1])
 */

public class Subset {
    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        int s = 100;
        Boolean exist = existSubset(arr, arr.length - 1, s);
        System.out.println(exist);

        boolean[][] subset = existSubset(arr, 7);
        for (int i = 0; i < subset.length; i++) {
            for (int j = 0; j < subset[i].length; j++) {
                System.out.print("\t" + subset[i][j]);
            }
            System.out.println();
        }

    }

    public static boolean[][] existSubset(int[] arr, int S) {
        boolean[][] subset = new boolean[arr.length][S + 1]; // s+1 是因为要考虑s=0
        // 初始化第一列
        for (int i = 0; i < arr.length; i++) {
            subset[i][0] = true;
        }
        // 初始化第一行
        for (int i = 0; i < S + 1; i++) {
            subset[0][i] = false;
        }
        if (arr[0] < S) {
            subset[0][arr[0]] = true;
        }

        // 更新其它
        for (int i = 1; i < arr.length; i++) { //从第二行开始，遍历每一行
            for (int s = 1; s < S + 1; s++){ // 从第二列开始，遍历没一列
                // 更新
                if (arr[i] > s){ // 如果当前值大于s，那么
                    subset[i][s] = subset[i-1][s];
                }else {
                    boolean select = subset[i][s-arr[i]];
                    boolean unSelect = subset[i-1][s];
                    subset[i][s] = select || unSelect;
                }
            }
        }
        return subset;
    }

    public static Boolean existSubset(int[] arr, int i, int s) {
        if (s == 0) {
            return true;
        }

        if (i == 0 && arr[i] != s) {
            return false;
        } else {
            Boolean select = false;
            if (s - arr[i] >= 0) {
                select = existSubset(arr, i - 1, s - arr[i]);
            }
            Boolean unSelect = existSubset(arr, i - 1, s);
            return select || unSelect;
        }
    }


}
