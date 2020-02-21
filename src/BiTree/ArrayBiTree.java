package BiTree;

/**
 * 顺序存储完全二叉树
 * 1、完全二叉树的root结点编号是从1开始，这里为了跟数组的索引对应上，所以这里完全二叉树的root结点编号从0开始
 * 2、root结点编号是0，如果n是一个父结点，那么其左子结点的编号为 2n + 1，右子结点的编号为 2n + 2；
 */
public class ArrayBiTree {
    public static void main(String[] args) {
        int[] biTree = {1,2,3,4,5,6,7};
        ArrayBiTreeDemo biTreeDemo = new ArrayBiTreeDemo(biTree);
        biTreeDemo.preOrderTraverse();
    }
}

class ArrayBiTreeDemo{
    private int[] arrayBiTree;

    public ArrayBiTreeDemo(int[] arrayBiTree) {
        this.arrayBiTree = arrayBiTree;
    }

    /**
     * 重载preOrderTraverse
     * 因为在调用preOrderTraverse方法的时候传入的参数总是root的索引值
     * 这里重载这个方法之后那么在调用的时候就不用传入参数了，更方便
     */
    public void preOrderTraverse(){
        preOrderTraverse(0);
    }

    /**
     * 前序遍历法遍历一个存储在数组里的完全二叉数
     * @param index
     */
    public void preOrderTraverse(int index){
        if (arrayBiTree.length == 0 || arrayBiTree == null){
            System.out.println("空树");
            return;
        }
        // 1、遍历根结点
        System.out.println(arrayBiTree[index]);

        // 2、遍历左子结点
        if ( (2 * index + 1) < arrayBiTree.length){
            preOrderTraverse(2 * index + 1);
        }
        // 3、遍历右子结点
        if ((2 * index + 2) < arrayBiTree.length){
            preOrderTraverse(2 * index + 2);
        }
    }
}
