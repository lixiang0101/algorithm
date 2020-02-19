package Search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找：对一个有序列表进行查找
 * 算法步骤：
 * 1、置算法查找区间，left=1，right为表长
 * 2、当left <= right时，循环以下操作：
 *      2.1、middle取值为left和right的中间值
 *      2.2、将findVal与middle处的值比较，如果相等，那么就查找成功，返回middle
 *      2.3、如果 findVal < array[middle] 那么说明要查找的值在middle的左边区间里，
 *           更新 right = middle - 1 ，因为middle处的值已经比较过里，所以要减1，也能时程序能退出
 *      2.4、反之，说明要找的值在middle的右区间里，更新 left = middle + 1；
 *  关键在于：middle - 1 和 middle + 1
 * 3、考虑：如果一个列表中右多个相同的值，而这个值恰好是要找的值，怎么把所有要找的值的index都返回？
 *    因为列表是有序的，只需要在 middle == findVal 后在 middle的左右扫描一下就可以
 *    怎么扫描？
 *      用一个临时指针tmp = middle - 1；依次往左遍历，当array[tmp] != findVal || tmp < 0时结束
 *      往右扫描和上面相反即可
 *    前提是：找到了middle，如果没找到，就没有扫描的必要了
 */

public class BinarySearch {
    public static void main(String[] args) {
        int[] array = {1,2,2,3,4,5,6,7};
        ArrayList<Integer> result = new ArrayList<>();
        List i = binarySearch2(array,0,array.length-1,10);
        System.out.println(i);
    }
    public static int binarySearch(int[] array, int left, int right, int findVal) {

        /*
        int l = left;
        int r = right;
        while (l <= r) {
            int middle = (l + r) / 2;
            if (array[middle] == findVal) {
                return middle;
            }else if (array[middle] > findVal) {
                r = middle - 1;
            } else {
                l = middle + 1;
            }
        }
        return -1;*/

        int middle = (left + right) / 2;
        if (left > right){
            return -1;
        }
        if (array[middle] > findVal){
            return binarySearch(array,left,middle-1,findVal);
        }else if (array[middle] < findVal){
            return binarySearch(array,middle+1,right,findVal);
        }else {
            return middle;
        }
    }

    public static List<Integer> binarySearch2(int[] array, int left, int right, int findVal) {


        int middle = (left + right) / 2;
        if (left > right) { // 没有找到middle
            return new ArrayList<>();
        }
        if (array[middle] > findVal) {
            return binarySearch2(array, left, middle - 1, findVal);
        } else if (array[middle] < findVal) {
            return binarySearch2(array, middle + 1, right, findVal);
        } else {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(middle);
            //向左扫描，看看左边还有一个相同的值
            int tmp = middle - 1;
            while (true){
                if (tmp < 0 || array[tmp] != findVal){
                    break;
                }
                result.add(tmp);

                tmp--;
            }
            //向右扫描，看看左边还有一个相同的值
            tmp = middle + 1;
            while (true){
                if (tmp > array.length - 1 || array[tmp] != findVal){
                    break;
                }
                result.add(tmp);

                tmp++;
            }
            return result;
        }
    }
}
