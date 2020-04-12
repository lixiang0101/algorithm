package Search;

/**
 * 剑指 offer：查找旋转数组的最小值，把一个非递减数组的前面部分元素搬到数组的后面
 * 比如A：{2,2,2,2,0,1,2} B：{4,5,6,7,8,1,2,3} C：{7,8,1,2,3,4,5,6}
 * 旋转数组的第一项肯定是比最后一项大的,如果不是那么就不是旋转数组，第一项就是最小值
 * 二分查找
 * 如果middle>=最后一位的值，那么最小值肯定在middle的后面，比如A，B
 * 如果middle<最后一位的值，那么最小值可能在middle的前面，也可能6就是middle，比如C
 *
 */

public class exercise01 {
    public static void main(String[] args) {
        int[] arr = new int[]{4,5};
        int min = solution(arr);
        System.out.println(min);
    }
    public static int solution(int[] arr) {
        if (arr.length == 0){
            throw new RuntimeException("");
        }

        int l = 0;
        int h = arr.length - 1;
        if (arr[l] < arr[h]){
            return arr[l];
        }

        while (l < h) {
            int mid = (l + h) / 2;
            if (arr[mid] >= arr[h]) {
                l = mid + 1;
            } else{
                h = mid; //
            }
        }
        return arr[l];
    }
}


