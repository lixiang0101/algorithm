package HuffmanTree;

import java.util.ArrayList;
import java.util.Collections;

public class huffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};

        Node huffmanTree = createHuffmanTree(arr);
        huffmanTree.preOrderTraverse();
    }
    public static Node createHuffmanTree(int[] arr){
        ArrayList<Node> f = new ArrayList<>();
        // 1、根据给的的n个权值{w1,w2,w3,....,wn}，构造n颗只有根结点的二叉树，这n颗二叉树构成一个森林F
        for (int i : arr) {
            f.add(new Node(i));
        }

        // 2、在森林F中选取两颗根结点的权值最小的树作为左右子树构造一颗新的二叉树，且置新的二叉树的根结点的权值为其
        // 左、右子树上根结点的权值之和
        // 3、从f中删除这两颗树，同时将新得到的二叉树加入到f中。
        // 4、重复2、3步，直到f只含一颗树为止。
        while (f.size() > 1){
            Collections.sort(f);
            Node leftNode = f.get(0);
            Node rightNode = f.get(1);
            Node newNode = new Node(leftNode.value + rightNode.value);
            newNode.leftChild = leftNode;
            newNode.rightChild = rightNode;
            f.add(newNode);
            f.remove(leftNode);
            f.remove(rightNode);
        }
        // 5、返回霍夫曼树的root结点
        return f.get(0);
    }
}

/**
 * 一定要实现Comparable接口
 */
class Node implements Comparable<Node>{
    public int value;
    public Node leftChild;
    public Node rightChild;

    public Node(int value) {
        this.value = value;
    }

    public void preOrderTraverse(){
        System.out.println(this);
        if (this.leftChild != null){
            this.leftChild.preOrderTraverse();
        }
        if (this.rightChild != null){
            this.rightChild.preOrderTraverse();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.value - o.value; // 升序排序
    }
}
