package Algorithm10;

/**
 * 分治算法
 * 汉诺塔问题
 */

public class DivideAndConquer {
    public static void main(String[] args) {
        hanoiTower(3,'A','B','C');
        System.out.println("移动完成，移动次数：" + count);
    }
    /**
     * 问题拆解：拆解成 最下面的盘子 + 其它盘子
     * 1、如果只有1个盘子，那么直接移动到 目标塔 就完成了
     * 2、如果盘子数大于1，
     * 2.1、先把其它盘子先移动到 中间塔 上，然后把最下面的盘子移动到 目标塔
     * 2.2、把中间塔上面的盘子移动到目标塔上
     * @param num 要移动的盘子数，1是最上面的那个，num是最下面的那个
     * @param s start 起始塔
     * @param b buffer 中间塔
     * @param t target 目标塔
     */
    public static void hanoiTower(int num,char s,char b, char t){
        count ++;
        if (num == 1){
            System.out.println("第" + num + "个盘子：" + s + " -> " + t);
        }else {
            // 把除最下面的那个盘子外的盘子移动到中间塔上 s -> b
            hanoiTower(num - 1,s,t,b);
            // 把最下面的盘子移动到目标塔上
            System.out.println("第" + num + "个盘子：" + s + " -> " + t);
            // 再把中间塔上的盘子移动到目标塔上
            hanoiTower(num-1,b,s,t);
        }
    }

    static int count = 0;

}
