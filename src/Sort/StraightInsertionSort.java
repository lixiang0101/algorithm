package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 直接插入排序 时间复杂度:O(n^2),最好：O(n),最坏：O(n^2)。空间复杂度：O(1)
 * 好比打扑克抓牌，每抓一张牌，就插入到合适的位置上，直到抓完牌为止，既可以得到一个有序序列
 * 思路：
 * 1、把n个待排序元素堪成一个有序表和一个无序表，无序表从第1个元素开始
 * 2、开始时有序表中只有一个元素（抓了第一张牌），无序表中有n-1个元素
 * 3、每次从无序表中取出第一个元素，让他依次跟有序表中的元素比较，并插入到正确的位置
 */

public class StraightInsertionSort {
    public static void main(String[] args) {
        int[] arr = {3, -1, 9, 2, 10, 4, 6, 5};
        int[] result = straightInsertionSort(arr);
        for (int i : result) {
            System.out.print(i + " ");
        }

        System.out.println(Arrays.toString(straightInsertionSort2(arr)));

        int[] nums = new int[80000];
        for (int i = 0; i < 80000; i++) {
            nums[i] = (int) (Math.random() * 80000); // Math.random生成的是0～1之间的随机数
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        straightInsertionSort(nums);

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);
    }

    public static int[] straightInsertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) { //依次从无序表中取出元素，无序表是从第1个元素开始
            for (int j = i; j > 0; j--) { //跟有序表中的元素比较
                if (array[j] < array[j - 1]) {
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
        }
        return array;
    }

    /**
     * 用for和while循环写的方法
     */
    public static int[] straightInsertionSort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int insertVal = array[i]; // 把无序表的首个元素拿出来，那么这个位置就相当于空了
            int insertIndex = i - 1;  // 将要插入的位置，初始位置是自己前面的这个位置

            // insertIndex >= 0 判断是否已经到达有序表的头部
            while (insertIndex >= 0 && insertVal < array[insertIndex]) {
                //如果要插入的元素比有序表中当前insertIndex这个位置的元素小，那么当前这个位置的元素就往后移动一位
                array[insertIndex + 1] = array[insertIndex];
                //待插入元素继续跟有序表的下一个元素比较
                insertIndex--;
            }
            //如果insertVal > array[insertIndex] while循环退出，把insertVal 的值插入到  insertIndex前面
            array[insertIndex + 1] = insertVal;
        }
        return array;
    }
}
