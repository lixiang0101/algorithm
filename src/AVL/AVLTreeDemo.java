package AVL;

/**
 * 平衡二叉树：又叫二叉排序树，或者二叉搜索树
 * 左旋转、右旋转、双旋转
 * 当一颗排序二叉树（BST）的左子树的高度远大于右子树的高度时（或者右大于左）
 * 查询速度会明细降低，因为即使左子树为空，每次仍然要比较左子树
 * 上面这种情况就可以用平衡二叉树解决
 */

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4,3,6,5,7,8};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.getRoot().inOrderTraverse();
        System.out.println("树高度：" + avlTree.getRoot().height());
        System.out.println("左子树高度：" + avlTree.getRoot().getLeft().height());
        System.out.println("右子树高度：" + avlTree.getRoot().getRight().height());
        System.out.println(avlTree.getRoot()); // 根结点已经发生了变化

        System.out.println("右旋转测试");
        int[] arr1 = {10,12,8,9,7,6};
        AVLTree avlTree1 = new AVLTree();
        for (int i = 0; i < arr1.length; i++) {
            avlTree1.add(new Node(arr1[i]));
        }
        avlTree1.inOrderTraverse();
        System.out.println("树高度：" + avlTree1.getRoot().height());
        System.out.println("左子树高度：" + avlTree1.getRoot().getLeft().height());
        System.out.println("右子树高度：" + avlTree1.getRoot().getRight().height());
        System.out.println(avlTree1.getRoot()); //根结点由12变成了8

        System.out.println("双旋转测试");
        int[] arr2 = {10,11,7,6,8,9};
        AVLTree avlTree2 = new AVLTree();
        for (int i = 0; i < arr2.length; i++) {
            avlTree2.add(new Node(arr2[i]));
        }
        avlTree2.inOrderTraverse();
        System.out.println("树高度：" + avlTree2.getRoot().height());
        System.out.println("左子树高度：" + avlTree2.getRoot().getLeft().height());
        System.out.println("右子树高度：" + avlTree2.getRoot().getRight().height());
        System.out.println(avlTree2.getRoot());
    }

}

class AVLTree{
    private Node root;

    public AVLTree(Node root) {
        this.root = root;
    }

    public AVLTree() {
    }



    public void add(Node node){
        if (this.root == null){
            this.root = node;
        }else {
            this.root.add(node);
        }
    }
    public void inOrderTraverse(){
        if (this.root == null){
            System.out.println("这是一个空树");
            return;
        }else {
            this.root.inOrderTraverse();
        }
    }

    public Node getRoot() {
        return root;
    }
}

/**
 * 根排序二叉树的结点一样，增加了一个求树高度的方法
 */
class Node{
    private int value;
    private Node left;
    private Node right;

    /**
     * 左旋转
     * 把当前结点为根结点的树 的右子结点设置成新的根结点，那么就要重新安顿三个结点：
     *  1、当前结点的左子树
     *  2、当前结点的右结点的左子树
     *  3、当前结点的右结点的右子树
     *
     * 1、创建一个新结点newNode
     *  newNode = 当前结点 --- 此时当前结点已经被新结点替代了
     *  newNode.left = 当前结点.left
     *  newNode.right = 当前结点.right.left
     * 2、更新当前结点
     *  当前结点.value = 当前结点.right.value
     *  当前结点.left = newNode
     *  当前结点.right = 当前结点.right.right
     *
     */
    public void leftRotate(){
        Node newNode = new Node(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = newNode;
    }

    /**
     * 右旋转，就是把当前根结点的左子结点作为新的根结点，当前根结点作为新根结点的右子结点
     * 1、左子结点的左子树不变，右子树作为当前根结点的左子树
     * 2、左子结点 的 右子结点 变成 当前的根结点
     */
    public void rightRotate(){
        // 以当前根结点的值创建新结点，也就是复制当前根结点
        Node newNode = new Node(this.value);
        // 上面第1步
        newNode.left = this.left.right;
        newNode.right = this.right;
        // 上面第2步
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }
    /**
     * 求以当前结点为根结的树的高度
     * 取当前结点左右子树的高度最大值，当前结点也算1个高度，所以加1
     * @return
     */
    public int height(){
        return (Math.max(this.left == null? 0 : this.left.height(),this.right == null ? 0:this.right.height())) + 1;
    }

    public int leftHeight(){
        if (this.left != null){
            return this.left.height();
        }
        return 0;
    }
    public int rightHeight(){
        if (this.right == null){
            return 0;
        }
        return this.right.height();
    }

    public void add(Node node){
        if (this.value > node.value){
            if (this.left != null){
                this.left.add(node);
            }else {
                this.left = node;
            }
        }else {
            if (this.right != null){
                this.right.add(node);
            }else {
                this.right = node;
            }
        }
        /**
         * 当前添加一个结点后，以当前结点为根结点的树的 右子树的高度 - 左子树的高度 > 1 进行左旋转
         */
        if (this.rightHeight() - this.leftHeight() > 1){
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                // 如果有这种情况要先 在当前结点的右子树上进行左旋转
                this.right.rightRotate();
                leftRotate();
            }else {
                leftRotate();
            }
            return; // 左旋转之后就退出，这个必须要！！！
        }

        if (this.rightHeight() - this.leftHeight() < -1){
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                // 如果有这种情况要先 在当前结点的右子树上进行左旋转
                this.left.leftRotate();
                rightRotate();
            }else {
                rightRotate();
            }
        }
    }
    public Node(int value){
        this.value = value;
    }

    public Node findTarget(int value){
        if (this.value == value){
            return this;
        }
        if (this.value > value){
            if (this.left == null){
                return null;
            }else {
                return this.left.findTarget(value);
            }
        }else {
            if (this.right == null){
                return null;
            }else {
                return this.right.findTarget(value);
            }
        }
    }

    public Node parent(Node targetNode){
        if ((this.left != null && this.left.value == targetNode.value) ||
                (this.right != null && this.right.value == targetNode.value)){
            return this;
        }else if (this.left != null && this.left.value < targetNode.value){
            return this.left.parent(targetNode);
        }else if (this.right != null && this.right.value > targetNode.value){
            return this.right.parent(targetNode);
        }
        return null;
    }

    public void inOrderTraverse(){
        if (this.left != null){
            this.left.inOrderTraverse();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.inOrderTraverse();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}