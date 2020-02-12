package Recursion;

/**
 * 递归算法之迷宫问题
 */

public class Migong {
    public static void main(String[] args) {
        //初始化地图
        int[][] map = new int[7][8];
        //构造墙
        for (int i = 0; i < 8; i++) {
            map[0][i] = 1;
            map[6][i] = 1;
        }
        for (int j = 0; j < 6; j++) {
            map[j][0] = 1;
            map[j][7] = 1;
        }
        for (int j = 2;j < 7;j++){
            map[4][j] = 1;
        }
        map[2][1] = 1;
        map[2][2] = 1;
        System.out.println("地图：");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


        setWay(map,1,1);
        System.out.println("路线：");
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    /**
     * 判断当前点的状态：0：未走过；1：障碍物；2：可走；3：走不通
     * 某个点能否走的通的判断依据：它的前后左右四个方向至少又一个方向能走的通
     * 判断的顺序不同，得到的路线也就不一样
     * 递归终止条件：运到到了出口位置 map[5][6] == 2
     * 建议使用 下--右--上--左 的策略
     * @param map 迷宫地图
     * @param i   每次运到的起始x轴
     * @param j   每次运到的起始y轴
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[5][6] == 2) {//递归终止
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; //先假设当前这个点可以走的通，就是说可以继续往下走，然后再去判断这个
                if (setWay(map, i+1, j)) { // 判断下
                    return true;
                } else if (setWay(map, i, j+1)) { // 判断右
                    return true;
                } else if (setWay(map, i-1, j)) { // 判断上
                    return true;
                } else if (setWay(map, i, j-1)) { // 判断左
                    return true;
                } else { // 说明四个方向都不通，那么前面的那个假设不成立
                    map[i][j] = 3;
                    return false; // 此条路就到此为止
                }
            } else { // map[i][j] != 0
                return false;
            }
        }
    }
}

