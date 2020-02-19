package Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 基数排序：桶排序的一个扩展，时间复杂度：O(n*k) k是桶的个数       空间复杂度：O(1)
 * 1、分桶：第1轮根据个位数分桶（10个桶），把列表里的数根据其个位数分到对应的桶里，
 *    所有数入桶完后，再按0～9号桶的顺序从每个桶里取出数覆盖原列表。出入桶按先进现出的顺序；
 *    第2轮按第1轮结束后列表的十位数来。。。
 *    重复上面这个过程，直到列表里最大数的最高位
 *    所以上面的过程一共需要执行几轮，由最大数的位数决定
 * 2、怎么算一个数的个位数、十位数、百位数...？
 *    个位数 = num/1 % 10
 *    十位数 = num/10 % 10
 *    百位数 = num/100 % 10
 * 3、每次堆排序需要创建11个数组，10个桶+1个记录桶有效数据的数组
 *    一个int大小为4byte，如果是对一个长8000w的列表进行排序，那么10个桶的大小为：
 *      10*4*800000000/1024/1024/1024 = 29.8G    !!!
 *    堆排序就是空间换时间
 */

public class RadixSort {
    public static void main(String[] args) {
        int[] array = {53,3,542,748,14,214};
        radixSort(array);
        System.out.println(Arrays.toString(array));

        System.out.println("**********测试基数排序处理8亿数据量所需时间**************");
        int[] nums = new int[80];
        for (int i = 0; i < 80; i++) {
            nums[i] = (int) (Math.random() * 800); // Math.random生成的是0～1之间的随机数
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        String start = simpleDateFormat.format(date);

        radixSort(nums);
        System.out.println(Arrays.toString(nums));
        String end = simpleDateFormat.format(new Date());
        System.out.println("排序开始时间：" + start);
        System.out.println("排序结束时间：" + end);
    }

    public static void radixSort(int[] array) {
        //1、找出列表中最大的数，并算出是几位数
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int maxLength = ("" + max).length();
        //2、初始化10个桶，每个桶的长度都为列表的长度
        int[][] bucket = new int[10][array.length];

        //3、初始化一个用来记录每个桶中有效数字个数的变量
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n = 1;i<=maxLength;i++,n *= 10){
            // 分桶
            for (int j = 0; j < array.length; j++) {
                // 计算出应该分到哪个桶
                int digitOfElement = array[j] / n % 10;
                // 入桶,bucketElementCounts[digitOfElement]是取出目标桶当前指针位置
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = array[j];
                bucketElementCounts[digitOfElement]++;
            }
            // 出桶
            int index = 0; // 原列表的指针
            for (int ii = 0;ii < 10; ii++){
                if (bucketElementCounts[ii] != 0){ // 判断桶中是否有数据
                    for (int j = 0;j < bucketElementCounts[ii];j++){
                        array[index++] = bucket[ii][j];
                    }
                    bucketElementCounts[ii] = 0; // 取出数据后，桶中有效数据个数归0
                }
            }
        }
    }
}
