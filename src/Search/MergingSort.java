package Search;

import java.util.Arrays;

/**
 * 归并排序：将两个或者两个以上的有序表合并成一个有序表的过程
 * 时间复杂度：O(nlogn) 最好最坏都一样，空间复杂度：O(1)
 */

public class MergingSort {
    public static void main(String[] args) {
        int[] nums = {2, 4, 6, 1, 3, 5, 7};
        //merge(nums, 0, 2, nums.length - 1);
        mSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 1、设两个有序表存放在同一数组中相邻的位置上：[low,middle],[middle,high]
     * 2、每次从这两个表中取出一个记录进行关键字的比较，将较小者按顺序放入到一个临时表tmp中，重复此过程，直到其中一个表位空
     * 3、最后将另一个非空表中剩余的部分直接复制到tmp中
     * 4、用tmp覆盖原数组array
     */
    public static void merge(int[] array, int low, int middle, int high) {
        // 初始化一个临时表和索引
        int[] tmp = new int[high - low + 1];
        int index = 0;

        int i = low; // 遍历左表的指针
        int j = middle + 1; //遍历右表的指针
        while (i <= middle && j <= high) {
            if (array[i] > array[j]) {
                tmp[index++] = array[j++];
            } else {
                tmp[index++] = array[i++];
            }
        }
        // 右边的表没有弄完
        while (j <= high) {
            tmp[index++] = array[j++];
        }
        //左边的表没有弄完
        while (i <= middle) {
            tmp[index++] = array[i++];
        }
        // 把临时表覆盖到原表
        for (int i1 = tmp.length - 1; i1 >= 0; i1--) {
            array[i1 + low] = tmp[i1];
        }
    }

    public static void mSort(int[] array, int low, int high) {
        int middle = (low + high) / 2;
        if (low < high) {
            // 向左递归进行分解 （分）
            mSort(array, low, middle);
            // 向右递归进行分解 （分）
            mSort(array, middle+1, high);
            // 合并
            merge(array, low, middle, high);
        }
    }
}
