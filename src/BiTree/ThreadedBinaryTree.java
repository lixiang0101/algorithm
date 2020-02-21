package BiTree;

/**
 * 中序线索化二叉树
 * 1、是一个递归线索化
 * 2、难点在于线索化当前结点
 * 3、需要一个临时指针 preNode，指向当前结点的前一个结点，那么preNode怎么更新呢？
 *    3.1、preNode的初始值是null
 *    3.2、当递归到第一个需要线索化的结点时，如果没有左子数，那么LChild就指向null
 *    3.3、然后给preNode线索化后继结点，只能在当前结点去线索化上一个结点的后继结点，也就是说preNode的后继结点指向的是当前结点
 *    3.3、线索化preNode的后继结点后，就把preNode更新为当前结点
 *    3.4、接着线索化当前结点的右子树
 */
public class ThreadedBinaryTree {
    public static void main(String[] args) {
        Node n1 = new Node(1, "一一");
        Node n2 = new Node(2, "二二");
        Node n3 = new Node(3, "三三");
        Node n4 = new Node(4, "四四");
        Node n5 = new Node(5, "五五");
        Node n6 = new Node(6, "六六");

        Tree tree = new Tree(n1);
        n1.leftChild = n2;
        n1.rightChild = n3;
        n2.leftChild = n4;
        n2.rightChild = n5;

        tree.inOrderTraverse();

        tree.inThreadedBinaryTree(n1);
        System.out.println("\n********检验中序线索化结果********");
        System.out.println(n3.leftChild);

        System.out.println("\n********中序线索化遍历********");
        tree.inThreadedBinaryTreeTraverse();

//        System.out.println("\n********后序线索化********");
//        tree.postThreadedBinaryTree(n1);
//        System.out.println(n2.rightChild);

    }
}

class Tree{
    private Node root;
    private Node preNode; // 做线索二叉树的时候会用到

    public Tree(Node root) {
        this.root = root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void inOrderTraverse(){
        if (root == null){
            System.out.println("当前树是一个空树");
            return;
        }
        this.root.inOrder();
    }

    /**
     * 中序线索二叉树
     * 1、先线索左子树，然后当前结点，然后右子树
     * @param node
     */
    public void inThreadedBinaryTree(Node node){
        if (node == null){
            return;
        }

        // 线索化左子树

        inThreadedBinaryTree(node.leftChild);

        // 线索化当前结点
        if (node != null && node.leftChild == null){ // 左子树为空的话，左指针领指向前驱
            node.leftChild = preNode;
            node.LTag = 1;
        }

        // 1、右指针域指向的是后继，所以要先知道后继是那个
        // 2、那么就要把当前指针往后面移动一个，移动之后旧node就变成来pre
        // 3、把旧node（也就是现在的pre）右孩子域指向新node
        if (preNode != null && preNode.rightChild == null){// 右子树为空的话，左指针领指向后继
            preNode.rightChild = node; // 注意！！！ 这里的node已经不是上面的node了，是一个新node，要理解这里
            preNode.RTag = 1;
        }

        // 每处理一个结点后，那么这个结点就成了preNode，要保存下来，用作下一个结点的线索化
        preNode = node;

        // 线索化右子树
        inThreadedBinaryTree(node.rightChild);
    }

    /**
     * 利用线索化遍历二叉树
     * 1、从root结点开始，先遍历左子树，找到左子树的第一个叶子结点，当一个结点的没有左子树，即LTag=1时，那么此结点就是一个叶子结点
     *
     */
    public void inThreadedBinaryTreeTraverse(){
        Node cur = root;

        while (cur != null) {
            while (cur.LTag != 1) {
                cur = cur.leftChild;
            }
            // 退出while循环就找到了左叶子结点
            System.out.println(cur);
            // 如果当前结点有后继结点那么就一直输出
            while (cur.RTag == 1){
                System.out.println(cur.rightChild);
                cur = cur.rightChild;
            }
            // 如果当前结点有右子树，那么就移动临时指针到当前结点的右子树的根结点上
            cur = cur.rightChild;
        }
    }

    /**
     * 后序线索二叉树 左-->右-->根
     * @param node
     */
    public void postThreadedBinaryTree(Node node){
        if (node == null){
            return;
        }
        postThreadedBinaryTree(node.leftChild);
        postThreadedBinaryTree(node.rightChild);

        if (node.leftChild == null){
            node.leftChild = preNode;
            node.LTag = 1;
        }
        if (preNode != null && preNode.rightChild == null){
            preNode.rightChild = node;
            preNode.RTag = 1;
        }
        preNode = node;
    }

}

class Node{
    public int id;
    public String name;
    public Node leftChild;
    public Node rightChild;
    public int LTag = 0;
    public int RTag = 0;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // 中序遍历 LDR
    public void inOrder(){
        if (this.leftChild != null){
            leftChild.inOrder();
        }
        System.out.println(this);
        if (this.rightChild != null){
            rightChild.inOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}