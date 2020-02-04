/**
 * 把二维数组转为稀疏数组
 */

public class SparseArray {
    public static void main(String[] args) {
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        System.out.println("原始数组");
        for (int[] row : chessArr1) {
            for (int item : row) {
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }

        /**
         * 二维数组转为稀疏数组
         * 1、遍历二维数组获取有效数据（非0个数）个数（sum）
         * 2、根据sum创建稀疏数组 int[sum+1][3]，稀疏数组的第一行是：【二维数组的行数】【二维数组的列数】【有效数据个数】
         * 3、将二维数组有效数据的坐标存到稀疏数组中 【行】【列】【值】
         */

        int sum = 0;
        for (int[] row : chessArr1) {
            for (int data : row) {
                if (data != 0){
                    sum ++;
                }
            }
        }

        int[][] sparseArr = new int[sum+1][3];
        sparseArr[0][0] = 11;//chessArr1的行数
        sparseArr[0][1] = 11;//chessArr1的列数
        sparseArr[0][2] = sum;

        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0 ){
                    count ++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println("转为稀疏数组后：");
        for (int[] row : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n",row[0],row[1],row[2]);
        }

        /**
         * 稀疏数组转为二维数组
         * 1、用稀疏数组的（0，0）和（0，1）两个值初始化二维数组
         * 2、恢复二维素组的有效数据
         */
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int[][] chessArr2 = new int[row][col];//初始化二维数组

        //恢复有效数据
        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println("稀疏数组转为二维数组结果：");
        for (int[] r : chessArr2) {
            for (int data : r) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
