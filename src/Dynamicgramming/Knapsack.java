package Dynamicgramming;

/**
 * 背包问题
 * 一个有固定容量(m)的背包，n个商品，每个商品都有固定的价值val和重量w，求怎么选择商品使得背包里装的物品价值最大
 * 思路：
 * 对于当前商品i，两种选择，装或者不装
 * 装：opt(i) = val[i] + opt(i-1,m-w[i])
 * 不装：opt(i) = opt(i-1,m)
 * 然后选最大值
 * 递归基：
 * 1、背包的剩余容量为0
 * 2、商品已经选完
 */

public class Knapsack {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000}; // 物品的价值
        int m = 4; // 背包的容量
        System.out.println(knapsackSolution(w, val, m));


    }

    public static int knapsackSolution(int[] w, int[] val, int m) {
        int n = w.length; // 物品的个数
        // 初始化一个二维数组,存放opt(i)，多出的一行和一列是递归基
        int[][] table = new int[n + 1][m + 1];
        // 更新第一列，即容量为0时
        for (int i = 0; i < table.length; i++) {
            table[i][0] = 0;
        }
        // 更新第一行
        for (int i = 1; i < table[0].length; i++) {
            table[0][i] = 0;
        }

        // 初始化一个二维数组，用来存放当前opt(i)下物品有没有放入到背包里
        int[][] path = new int[n + 1][m + 1];

        // 按行来更新
        for (int i = 1; i < table.length; i++) {
            for (int j = 1; j < table[i].length; j++) {
                if (w[i - 1] > j) { // 如果当前物品的重量比包的容量大，那么当前物品肯定不能放到包里，只能不选
                    // 这里是w[i-1]是因为从第1行和第1列开始更新二维表
                    table[i][j] = table[i - 1][j];
                } else {
                    int select = val[i - 1] + table[i - 1][j - w[i - 1]]; // 选择当前的物品
                    int unSelect = table[i - 1][j]; // 不选当前的商品
                    if (select > unSelect) {
                        table[i][j] = select;
                        path[i][j] = 1; // 只有这种情况，当前物品才会放入到背包里
                    } else {
                        table[i][j] = unSelect;
                    }
                }
            }
        }
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                System.out.print("\t" + table[i][j]);
            }
            System.out.println();
        }

        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入背包，"
                        + "物品价值：" + val[i - 1] + "\t物品重量：" + w[i - 1]);
                j -= w[i - 1]; // 更新剩余容量
            }
            i--;
        }

        return table[n][m];
    }
}
