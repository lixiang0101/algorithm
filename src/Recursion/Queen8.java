package Recursion;

import java.util.ArrayList;

/**
 * 递归8皇后问题
 * 描述：
 *  在一个8✖️8的棋盘上，摆放8个皇后，每个两个皇后不能在同一行或者同一列或者同一对角线上（45度对角线）
 * 每个皇后摆放的位置放在一个数组里{ , , , , , , , }，每个数的index代表第几个皇后，数值代表该皇后在哪一列
 * 思路：
 *  从第一行开始，在8个位置上都尝试放一下，每次放置时先判断是否与前面的皇后冲突（judge函数），
 *  如果不冲突就继续放下一个皇后（递归调用放置皇后函数，check函数），。。。，然后参数下一个位置
 *  如果冲突，就不再继续放一下个皇后，尝试下一个位置
 */

public class Queen8 {
    int[] array = new int[8];
    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("判段冲突的次数为%d",count);
    }
    public static int count = 0;
    /**
     * 放置第n个皇后的方法
     * 1、把第n个皇后依次放在改行的每个位置，那么这个皇后有8种放法 array[n] = i
     * 2、每放在一个位置时都需要检查一下与前面的皇后是否发生冲突
     * 3、如果与前面的皇后没有冲突，那么继续放下一个皇后，直到放第9个皇后时执行print()方法，
     *    此时并没有结束，而是把当前皇后往后移动一个位置，继续判断。。。
     * 4、如果与前面的皇后冲突，那么 if (judge(n)) {...} 不会执行，也就不会继续放后面的皇后，
     *    而是把当前皇后往后移动一个位置，继续判断。。。
     * @param n
     */
    private void check(int n){
        if (n == 8) { // 但检查到第9个皇后时，说明前8个皇后已经放好了
            print();
            return;
        }
        for (int i = 0;i < 8;i++) {
            array[n] = i;
            if (judge(n)) { //如果与前面的皇后不冲突，然后放后面的皇后
                check(n + 1);
            }
        }
    }
    /**
     * 判断第n个皇后跟前面的皇后是否冲突
     * @param n 要判断的皇后所在行
     * @return
     */
    private boolean judge(int n){
        count++;
        for (int i = 0; i < n; i++){
            //(Math.abs(array[n] - array[i]))/(Math.abs(n-i)) == 1 根据斜率判断是不是在对角线上
            //array[i] == array[n] 判断是不是在同一列上
            if (array[i] == array[n] || Math.abs(array[n] - array[i]) == Math.abs(n-i) ){
                return false;
            }
        }
        return true;
    }

    /**
     * 打印结果
     */
    private void print(){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
