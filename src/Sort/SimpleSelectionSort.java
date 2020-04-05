package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单选择排序 时间复杂度:O(n^2),最好最坏都一样。 空间复杂度 O(1)
 * 1、设待排序的记录存放在数组r[1...n]中。第一趟从r[1]开始，通过n-1次比较，
 * 从n个记录中选出关键字最小的资料，记为r[k]，交换r[1]和r[k]。
 * 2、第二趟从r[2]开始，通过n-2次比较，从n-1个记录中选出关键字最小的记录，
 * 记为r[k]，交换r[2]和r[k]
 * 3、依次类推，第i趟从r[ℹ]开始，通过n-i次比较，从n-i+1个记录中选出关键字最小的记录，
 * 记为r[k]，交换r[i]和r[k]。
 * 4、经过n-1趟，排序完成
 * <p>
 * 一共有n-1轮排序
 * 时间复制度：n的平方
 */

public class SimpleSelectionSort {
    public static void main(String[] args) {
        int[] arr = {3, -1, 9, 2, 10, 4, 6, 5};
        int[] result = simpleSelectionSort(arr);
        for (int i : result) {
            System.out.print(i + " ");
        }

        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] = (int) (Math.random() * 80000); // Math.random生成的是0～1之间的随机数
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        simpleSelectionSort(nums);

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);
    }

    public static int[] simpleSelectionSort(int[] array) {
        int min;
        int minIndex;
        //从第1个元素开始，在后面的无序列表中找到最小值，与第1个元素进行交换
        for (int i = 0; i < array.length; i++) {
            min = array[i];
            minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) { //找到了最小值
                array[minIndex] = array[i];
                array[i] = min;
            } //否则最小值就是自己
        }
        return array;
    }
}
