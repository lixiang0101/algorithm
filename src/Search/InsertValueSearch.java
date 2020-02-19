package Search;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 插值法查找：对二分查找的一种优化
 * 1、二分查找在计算middle时比较简单，插值查找利用线性插值公式来计算middle
 * 2、二分查找：middle = (left + right) / 2 = left + 1/2 * (right - left)
 * 3、在插值插找里，主要是对上面的 1/2 进行优化，1/2改成:
 *      (findVal - arr[left]) / (arr[right] - arr[left])
 *    此时需要考虑 待查找的 findVal < arr[left] 那么：middle < 0 或者 findVal > arr[right] 那么：middl > right
 */

public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100;i++){
            arr[i] = i;
        }
        ArrayList<Integer> result = insertValueSearch(arr, 0, arr.length - 1, 2);
        System.out.println(result);
    }

    public static ArrayList<Integer> insertValueSearch(int[] array, int left, int right, int findVal) {
        System.out.println("插值查找法");
        // 注意：findVal < array[left] || findVal > array[right] 是必须的，否则可能会报ArrayIndexOutOfBoundsExceptionS
        if (left > right || findVal < array[left] || findVal > array[right]) {
            return new ArrayList<Integer>();
        }

        int middle = left + (findVal - array[left]) / (array[right] - array[left]) * (right - left);
        if (array[middle] > findVal) {
            return insertValueSearch(array, left, middle - 1, findVal);
        } else if (array[middle] < findVal) {
            return insertValueSearch(array, middle + 1, right, findVal);
        } else {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(middle);
            int tmp = middle - 1;
            while (true) {
                if (tmp < 0 || array[tmp] != findVal) {
                    break;
                }
                result.add(tmp--);
            }

            tmp = middle + 1;
            while (true) {
                if (tmp > array.length - 1 || array[tmp] != findVal) {
                    break;
                }
                result.add(tmp++);
            }
            return result;
        }
    }
}
