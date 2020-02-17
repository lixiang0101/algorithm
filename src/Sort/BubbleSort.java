package Sort;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 冒泡排序  时间复杂度：O(n^2),最好：O(n),最坏：O(n^2)    空间复杂度：O(1)
 * 冒泡排序是交换排序：
 * 两两比较待排序记录的关键字，一旦发现两个记录不满足次序要求时就进行交换，直到整个序列全部有序；
 * <p>
 * 优化：
 * 如果在某趟排序中，没有发生交换，那么就可以结束排序
 */

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {3, -1, 9, 2, 10, 4, 6, 5};
        int[] result = bubbleSort(arr);
        for (int i : result) {
            System.out.print(i + " ");
        }

        //冒泡排序进行大批量数据测试，对随机生成的8w个数进行排序
        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] = (int) (Math.random() * 80000); // Math.random生成的是0～1之间的随机数
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        bubbleSort(nums);

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);

    }

    public static int[] bubbleSort(int[] array) {
        boolean flag = false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    flag = true; // flag用了记录是否发生过交换
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        if (!flag) { // 如果某趟没有发生交换，排序结束，程序退出
            return array;
        } else { // 如果发生交换，flag的值会在交换的时候改变，所以在这里需要重置一下
            flag = false;
        }
        return array;
    }
}
