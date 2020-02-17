package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序：由冒泡排序改进而得
 * 1、在待排序的n个记录中任取一个记录（通常是第一个记录）作为枢轴（pivotKey）。
 * 2、经过一趟排序后，把所有关键字小于pivotKey的记录交换到前面，把所有大于关键字pivotKey的记录交换到后面，形成两个子表
 *    把pivotKey放在两个子表分解处的位置。
 * 3、分别对两个子表重复上述过程，直到每一个子表只有一个记录时，排序完成。
 */

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {4, 3, 5, -2, 5, 7, -3, -5, 9};
        int[] result = quickSort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(result));

        int[] nums = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            nums[i] = (int) (Math.random() * 8000000); // Math.random生成的是0～1之间的随机数
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        int[] res = quickSort(nums,0,nums.length-1);

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);

    }

    /**
     * @param array 待排序数组
     * @param start 数组第一个数的索引
     * @param end   数组最后一个数的索引
     * @return
     */
    public static int[] quickSort(int[] array, int start, int end) {
        if (start < end) { //递归退出条件，就是子表只剩下一个元素了
            int pivot = array[start]; // 把第一个数作为pivotKey
            int low = start; // 左指针：用来从左往右遍历
            int high = end;  // 右指针：用了从右往左遍历

            while (low < high) { // 直到 low = high退出循环
                // 先从右往左遍历，因为左边的第一个数的位置已经空出来了，第一个数交给了pivot
                while (low < high && array[high] >= pivot) {
                    high -= 1;
                }
                array[low] = array[high]; // 把右边比pivot小的数移动到左边

                while (low < high && array[low] <= pivot) {
                    low += 1;
                }
                array[high] = array[low]; // 把左边比pivot大的数移动到右边
            }
            array[low] = pivot; // 把pivot移动到左右子表的分界处，此时low = high

            // 递归排序左子表
            quickSort(array, start, low);
            // 递归排序右子表
            quickSort(array, low + 1, end);
        }
        return array;
    }
}
