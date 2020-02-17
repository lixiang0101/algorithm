package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * 时间复杂度：O(nlogn)       空间复杂度：O(1)
 */

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {3, -1, 9, 2, 10, 4, 6, 5};
        int[] result = shellSort(arr);
        System.out.println(Arrays.toString(result));

        System.out.println(Arrays.toString(shellSort2(arr)));

        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] = (int) (Math.random() * 80000); // Math.random生成的是0～1之间的随机数
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        int[] res = shellSort2(nums);

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);

    }

    /**
     * 交换法希尔排序
     * 1、分组：第1次分组间隔gap为数组长度一半，第2次分组按第1次分组组距的一半，依次类推，直到分组间隔为1；
     * 所以间隔为gap的放在一组；
     * 2、遍历：每次分组后，遍历每组中的两个数，从第1组的组尾i开始，（array[i-gap]，array[i]）就是一组，i++
     * 3、排序：比较 array[i-gap]和array[i] 大小，并根据结果交换位置，如果 i-gap-gap 位置有数，
     * 那么要继续比较 array[i-gap-gap] 和 array[i-gap]，如果 i-gap-gap-gap 位置有数，依次类推。。。
     *
     * @param array
     * @return
     */

    public static int[] shellSort(int[] array) {
        int tmp = 0;
        // gap 分组大小，每进行一轮后，组大小就缩小一半
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                // 遍历各组中所有元素，每组中有两个元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > array[j + gap]) { //
                        tmp = array[j];
                        array[j] = array[j + gap];
                        array[j + gap] = tmp;
                    }
                }
            }
        }
        return array;
    }

    /**
     * 移位法希尔排序
     * 分组：初始分组间隔为：array.length / 2，然后每次缩小2倍
     * 移动：每次分组之后。。。
     * @param array
     * @return
     */
    public static int[] shellSort2(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int insertVal = array[i]; // 对第i个数进行插入排序
                int j = i;
                if (insertVal < array[j - gap]) { // 如果当前的这个数比它前面的这个数大，那么就不用再插入了
                    while (j - gap >= 0 && insertVal < array[j - gap]) {
                        array[j] = array[j - gap]; // 把array[j - gap]往后挪动一位
                        j -= gap;
                    }
                    array[j] = insertVal;
                }
            }
        }
        return array;
    }
}
