package Algorithm10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * 全排列算法，递归思路
 */
public class Permutation {
    public static void main(String[] args) {
        int[] arr = {1,2,2};
        permutation(arr,0,arr.length);

    }

    public static void permutation(int[] arr,int low,int high){
        if (arr.length == 0){
            throw new RuntimeException("这是一个空数组！");
        }
        if (low == high){ // 当递归到最后一个元素时递归结束
            System.out.println(Arrays.toString(arr));
        }else {
            HashSet<Integer> set = new HashSet<>();
            for (int i = low ; i < high; i++) { // 把low后面的元素依次跟low进行交换
                if (set.contains(arr[i])) continue;// 如果要交换的数在前面已经有了，那么就不交换，比如{1,2,2}，就不会交换第2个2
                set.add(arr[i]);
                swap(arr, low, i); // 用当前位置后的每一个元素与当前元素交换，然后对后面的元素进行全排列
                permutation(arr,low+1,high);
                swap(arr,i,low); // 全排列后，要把上面交换的元素给交换回来
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
