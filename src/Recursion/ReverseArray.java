package Recursion;

import java.util.Arrays;

/**
 * 将一个数组里面的元素反转，用递归方法
 * 这是一种减而治之的思想
 */

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        reverse(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }


    public static void reverse(int[] arr, int low, int high) {
        if (low < high){
            int tmp;
            tmp = arr[high];
            arr[high] = arr[low];
            arr[low] = tmp;
            reverse(arr,++low,--high);
        }
    }


}
