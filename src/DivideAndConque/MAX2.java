package DivideAndConque;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 * 找出一个数组中最大的两个值
 */

public class MAX2 {
    public static void main(String[] args) {
        int arr[] = {3, 7, 2, 6, 9, 12, 34, 45, 8, 101, 101, 23, 19};
        System.out.println(arr.length);
        int[] res = max2_3(arr, 0, arr.length);
        System.out.println(Arrays.toString(res));

        //对三种方法进行时间测试
        int[] nums = new int[800];
        Random random = new Random();
        random.setSeed(1);
        for (int i = 0; i < 800; i++) {
            nums[i] = (int) (random.nextInt(800)); // Math.random生成的是0～1之间的随机数
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        int[] result = max2_3(nums, 0, nums.length);
        System.out.println(Arrays.toString(result));

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);
    }

    /**
     * 用迭代的方法完成
     * 三趟遍历的总的是时间复杂度为：（high - low - 1）+（x1 - low - 1）+（high - x1 - 1） = 2n - 3
     * 无论数组是怎么样的，三趟遍历都是固定的，所以时间复杂度最好和最坏都一样
     *
     * @param arr  arr[low,high)
     * @param low  数组的第一个元素的index
     * @param high 数组的最后一个index+1
     */
    public static int[] max2_1(int[] arr, int low, int high) {
        // 第一趟遍历，找出最大值x1，遍历次数high - low - 1
        int x1 = low;
        for (int i = low + 1; i < high; i++) {
            if (arr[i] > arr[x1]) {
                x1 = i;
            }
        }
        // 第二趟，从arr[low,x1)和arr(x1,high)中找出次最大值x2，遍历次序 x1 - low - 1
        int x2 = low;
        for (int i = low + 1; i < x1; i++) {
            if (arr[i] > arr[x2]) {
                x2 = i;
            }
        }
        // 遍历次数 high - x1 - 1
        for (int i = x1 + 1; i < high; i++) {
            if (arr[i] > arr[x2]) {
                x2 = i;
            }
        }
        int[] result = {x1, x2};
        return result;
    }

    /**
     * 第一种方法的优化，每次比较同时维护x1和x2，如果当前位置的值大于x2，再去跟x1比
     * 在最好情况：1 +（n-2）*1 = n -1 最好情况相比max2_1的算法已经有所改善
     * 最坏的情况：1 + (n-2)*2 = 2n - 3 最坏情况下，并没有改善
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] max2_2(int[] arr, int low, int high) {
        int x1 = low;
        int x2 = low + 1;
        if (arr[x1] < arr[x2]) { // 这里需要执行一次
            x1 = low + 1;
            x2 = low;
        }

        for (int i = low + 2; i < high; i++) { // 会循环n-2次
            if (arr[i] > arr[x2]) { // 最好最坏这里都要比较一次
                x2 = i;
                if (arr[x2] > arr[x1]) { // 最坏情况，每次这里都返回true
                    int tmp = x1;
                    x1 = x2;
                    x2 = tmp;
                }
            }
        }
        int[] result = {x1, x2};
        return result;
    }

    /**
     * 递归+分治的算法
     * 递归基：数组中有2个或者3个元素时，因为要找最大的两个值，所以当数组中有3个值时，没法拆，所以要退化处理
     * 复杂度：5n/3-2
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] max2_3(int[] arr, int low, int high) {
        int[] result = {low, low + 1};
        if (high == low + 2) { // 退化情况1：只有两个元素
            if (arr[low] < arr[low + 1]) {
                result[0] = low + 1;
                result[1] = low;
            }
            return result;
        }
        if (high == low + 3) {// 退化情况2：只有三个元素
            if (arr[low] > arr[low + 1]) {
                if (arr[low + 1] < arr[low + 2]) {
                    if (arr[low] > arr[low + 2]) {//[x0,x2]
                        result[1] = low + 2;
                    } else { //[x2,x0]
                        result[0] = low + 2;
                        result[1] = low;
                    }
                    //[x0,x1]
                }
            } else {
                if (arr[low + 1] < arr[low + 2]) { //[x2,x1]
                    result[1] = low + 1;
                    result[0] = low + 2;
                } else {
                    if (arr[low] < arr[low + 2]) {
                        result[0] = low + 1;
                        result[1] = low + 2;
                    } else {
                        result[0] = low + 1;
                        result[1] = low;
                    }
                }
            }
            return result;
        }
        // 分
        int middle = (low + high) / 2;
        int[] result_l = max2_3(arr, low, middle);
        int[] result_r = max2_3(arr, middle, high);
        // 合
        if (arr[result_l[0]] > arr[result_r[0]]) { //比较左右的最大值
            result[0] = result_l[0];
            // 如果左边的最大值大于右边的最大值，那么就只需比较左边的x2和右边的x1即可
            if (arr[result_l[1]] > arr[result_r[0]]) {
                result[1] = result_l[1];
            } else {
                result[1] = result_r[0];
            }
        } else { // 比较逻辑和上面一样
            result[0] = result_r[0];
            if (arr[result_r[1]] > arr[result_l[0]]) {
                result[1] = result_r[1];
            } else {
                result[1] = result_l[0];
            }
        }

        return result;
    }

}

