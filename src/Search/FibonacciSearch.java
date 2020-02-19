package Search;

import java.util.Arrays;

/**
 * 非波拉契查找法：继续是在优化mid
 *      mid = low + f[k-1] - 1
 *  1、非波拉契定理：f[k] = f[k-1] + f[k-2] {1,2,3,5,8,13,21...}
 *     可推出 f[k] - 1 = (f[k-1] - 1) + (f[k-2] - 1) + 1
 *  2、如果一个顺序表的长度为 f[k] - 1，那么可以用非波拉契数把这个顺序表拆为两部分：
 *      第一段长度为：f[k-1] - 1，第二段长度为：f[k-2] - 1
 *      等式右边多出来的那个1就是mid的位置
 *      mid = low + f[k-1] - 1
 *  3、第一次被拆的两段还可以继续用上面这种方法拆
 *  举例：
 *      {1,2,3,4,5,6,7}
 *     当k = 4时，f[4] - 1 = 7 等于顺序表的长度
 *     第1次拆：
 *          f[4]-1 = f[3]-1 + f[2]-1 + 1 = 4 + 2 + 1
 *          mid = 0 + f[4-1] - 1 = 0+4 = 4
 *          {1,2,3,4} 5 {6,7}
 *     第2次拆
 *          左边{1,2,3,4} 此时k--,f[3]-1 = f[4-1] -1 = f[2]-1 + f[1]-1 + 1 = 2 + 1 + 1
 *          mid = 0 + f[3-1] -1 = 2
 *          {1,2} 3 {4}
 *
 *          右边{6,7}，此时k-=2 ，f[2] - 1 = f[4-2] -1 = f[1]-1 + f[0]-1 + 1 = 1 + 0 + 1
 *          mid = 5 + f[4-1-2] - 1 = 5+2-1 = 6
 *          {6,7} {}
 *   思考：找到一个跟原列表长度大小一样的非波拉契数，利用非波拉契数的性质，将列表拆分成前后两部分，
 *         如果继续拆前一个表那么就k-1，如果拆后一个表那么就k-2
 */

public class FibonacciSearch {
    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100;i++){
            array[i] = i;
        }
        System.out.println(fibonacciSearch(array,0,array.length-1,2));
    }

    public static int fibonacciSearch(int[] arr,int low ,int high,int findVal){
        int mid = 0;
        int[] f = fib(20);
        // 找到一个大于等于high的非波拉契数
        int k = 0; // 非波拉契数在非波拉契数组中所对应的索引
        while (f[k] - 1 < high){
            k++;
        }
        // 因为非波拉契不是连续的，所以找到的k可能存在 f[k] > high，这时要用原列表的最后一个数对原列表进行扩充
        int[] tmp = Arrays.copyOf(arr, f[k]);
        for (int i = high + 1;i < f[k];i++){
            tmp[i] = arr[high];
        }

        while (low < high){
            System.out.println("非波拉契查找法查找");
            mid = low + f[k-1] - 1; // 根据公式计算出mid
            if (arr[mid] > findVal){ // 接着往左边查找
                high = mid - 1;
                k--;
            }else if (arr[mid] < findVal){
                low = mid + 1;
                k -= 2;
            }else {
                if (mid < high) {
                    return mid;
                }
                return high;
            }
        }
        return -1;
    }
    /**
     * 用非递归法来获取一个非波拉契数列
     * @param n
     * @return
     */
    public static int[] fib(int n) {
        int[] result = new int[n];
        if (n == 1) {
            result[0] = 1;
        }else if (n == 2) {
            result[0] = 1;
            result[1] = 2;
        }else {
            result[0] = 1;
            result[1] = 2;
            for (int i = 2; i < n;i++){
                result[i] = result[i-1] + result[i-2];
            }
        }
        return result;
    }
}
