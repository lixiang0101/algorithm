package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 堆排序
 */

public class HeapSort {
    public static void main(String[] args) {
        int [] arr = {4,6,8,5,9};
        createHeap(arr);
        System.out.println(Arrays.toString(arr));

        heapSort(arr);
        System.out.println(Arrays.toString(arr));

        int[] nums = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            nums[i] = (int) (Math.random() * 8000000); // Math.random生成的是0～1之间的随机数
        }
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        heapSort(nums);

        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);
    }

    /**
     * 1、把无序数组调整成一个最大堆数组
     * 2、把堆顶元素和二叉树最后一个叶子结点交换
     * 3、调整堆顶元素，因为此时只有对顶元素不合格
     * @param arr
     */
    public static void heapSort(int[] arr){
        createHeap(arr);
        for (int i = arr.length - 1; i > 0; i --){
            int tmp = arr[0];
            arr[0] = arr[i];
            arr[i] = tmp;
            heapAdjust(arr,0,i);
        }
    }

    /**
     * 1、在一个完全二叉树中，所有序号大于 n/2(向下去整) 的结点都是叶子结点，因此，以这些结点为根的子树已经是堆，不用调整。
     * 2、这样，只需用筛选法，从最后一个分之结点 n/2开始，依次将序号为n/2、n/2 - 1、n/2 - 2、n/2 - 3 ...、1的结点作为根
     * 的子树都调整为堆即可。
     * 3、在数组中，索引编号从0开始，所以上面的都要减1
     * @param arr 无序数组
     */
    public static void createHeap(int[] arr){
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0;i--){
            heapAdjust(arr,i,n);
        }
    }

    /**
     * 1、从arr[2*i+1]和arr[2*i+2] 中选出较大者，假设arr[2*i+1]较大，比较arr[i]和arr[2*i+1]的大小
     * 2、如果 arr[i] > arr[2*i+1]，说明以arr[i]为根的子树已经是堆，不必要左任何调整
     * 3、如果 arr[i] < arr[2*i+1]，交换 arr[i] 和 arr[2*i+1]。交换后，以arr[2*i+2]为根的子树不受交换的影响，仍是堆。
     *    如果以arr[2*i+1]为根的子树不是堆，则重复上述过程。将以arr[2*i+1]为根的子树调整为堆，直到进行到叶子结点为止。
     * @param arr 需调整的数组
     * @param i 调整的第i个子树
     * @param n 需调整的数组中元素个数
     */
    public static void heapAdjust(int[] arr,int i,int n){
        int tmp = arr[i]; // 把第1次调整的根记录下来
        for (int j = 2 * i + 1; j < n; j = j * 2 + 1){
            // 1、从arr[2*i+1]和arr[2*i+2] 中选出较大者
            if ( j + 1 < n && arr[j] < arr[j+1]){ // j + 1 < length 可以提高效率
                j+=1;
            }
            // 2、如果 arr[i] < arr[2*i+1]，交换 arr[i] 和 arr[2*i+1]
            if (tmp < arr[j]){
                arr[i] = arr[j];
                // 交换后，做好继续调整以arr[j]为根的子树的准备，进入下一轮for循环
                i = j;
            }else { // 3、说明以arr[i]为根的子树已经是堆，不必要左任何调整
                break;
            }
        }
        arr[i] = tmp; // 调整结束
    }
}
