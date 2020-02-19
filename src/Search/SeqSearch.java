package Search;

/**
 * 线性查找
 */

public class SeqSearch {
    public static void main(String[] args) {
        int[] array = {-1,2,3,7,1,2,3,4};
        System.out.println(seqSearch(array, 2));
    }

    public static int seqSearch(int[] array,int key){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == key){
                return i;
            }
        }
        return -1; // 如果在列表里没有找到就返回-1
    }
}
