package BiTree;

/**
 * 二叉排序树：根结点的值大于左结点的值，小于右结点的值，用中序遍历的结果是有序的
 * 二叉排序树删除结点：
 *
 * 第1种：删除叶子结点：
 * 1、找到要删除的目标结点：targetNode
 * 2、找到targetNode的父节点：parent
 * 3、判断targetNode是parent的左子结点还是右字节点
 * 4、根据3的结果删除targetNode：
 *  1）parent.left = null
 *  2) 或者 parent.right = null
 *
 * 第2种：删除只有一颗子树的结点
 * 1～3步和第1种情况一样
 * 4、如果targetNode有左子树：
 *  1）如果targetNode是parent的左子结点：parent.left = targetNode.left
 *  2）如果targetNode是parent的右子结点：parent.right = targetNode.left
 * 5、如果targetNode有右子树
 *  1）如果targetNode是parent的左子结点：parent.left = targetNode.right
 *  2）如果targetNode是parent的右子结点：parent.right = targetNode.right
 *
 * 第3种：删除有两颗子树的结点(难点)
 * 1～2步和前面两种情况一样
 * 3、从targetNode的右子树里找到最小的结点minNode，并用一个临时结点tmp指向minNode（单独写一个方法）
 * 4、删除minNode，用minNode替换targetNode
 */

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int arr[] = {7,3,10,12,5,1,9,0};
        BinarySortTree tree = new BinarySortTree();

        for (int i = 0; i < arr.length; i++) {
            tree.add(new Node1(arr[i]));
        }

        tree.inOrderTraverse();

        System.out.println("\n删除叶子结点");
        tree.deleteNode(1);
        tree.inOrderTraverse();
    }


}

class BinarySortTree{
    private Node1 root;

    public BinarySortTree() {
    }

    public BinarySortTree(Node1 root) {
        this.root = root;
    }

    public void add(Node1 node){
        if (this.root == null){
            this.root = node;
            return;
        }
        this.root.add(node);
    }

    public void inOrderTraverse(){
        if (this.root == null){
            return;
        }
        this.root.inOrderTraverse();
    }

    public Node1 findTarget(int value){
        if (this.root == null){
            System.out.println("当前树是一个空树");
            return null;
        }
        Node1 target = this.root.findTarget(value);
        return target;
    }

    public Node1 findParent(Node1 targetNode){
        if (this.root == null){
            return null;
        }
        Node1 parent = this.root.findParent(targetNode);
        return parent;
    }

    public void deleteNode(int value){
        if (this.root == null){
            return;
        }else {
            Node1 targetNode = findTarget(value);
            if (targetNode == null) {
                System.out.println("要删除的结点不存在");
                return;
            }
            // 如果root的左右结点都是null，那么要删除的结点就是root结点
            if (this.root.left == null && this.root.right == null) {
                this.root = null;
                return;
            }

            Node1 parentNode = findParent(targetNode); // 到这里才有必要查找父结点
            //第1种情况
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode.left != null && parentNode.left.value == value) {
                    parentNode.left = null;
                } else if (parentNode.right != null && parentNode.right.value == value){
                    parentNode.right = null;
                }
            }else if (targetNode.left != null && targetNode.right != null){ // 第3种情况
                int minValue = deleteRightChildMinNode(targetNode.right);
                targetNode.value = minValue;
            }else{ // 剩下的就是第2种情况，这是一种简便的写法
                if (targetNode.left != null){ // 如果targetNode有左子结点
                    if (parentNode != null){ // 如果target有父结点
                        if (parentNode.left.value == value){ // 如果targetNode是parent的左子树
                            parentNode.left = targetNode.left;
                        }else {
                            parentNode.right = targetNode.left;
                        }
                    }else{ // 如果targetNode没有父结点，即targetNode是root结点，且这个root结点只有左子树
                        root = targetNode.left;
                    }
                }else { // 如果targetNode有右子结点
                    if (parentNode != null){
                        if (parentNode.left.value == value){
                            parentNode.left = targetNode.right;
                        }else {
                            parentNode.right = targetNode.right;
                        }
                    }else {// 如果targetNode没有父结点，即targetNode是root结点，且这个root结点只有右子树
                        root = targetNode.right;
                    }
                }
            }
        }
    }

    /**
     * 找出targetNode右子结点的最小结点并删除，然后把最小结点的值返回
     * @param rightChild
     * @return
     */
    public int deleteRightChildMinNode(Node1 rightChild){
        Node1 minNode = rightChild;
        while (minNode.left != null){
            minNode = minNode.left;
        }
        deleteNode(minNode.value);
        return minNode.value;
    }
}

class Node1{
    int value;
    Node1 left;
    Node1 right;

    public Node1(int value) {
        this.value = value;
    }

    public void add(Node1 node){
        if (this.value > node.value){
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        }else {
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    public Node1 findTarget(int value){
        if (this.value == value){
            return this;
        }
        if (this.value < value && this.right != null){
            return this.right.findTarget(value);
        }else if (this.value < value && this.right == null){
            return null; // 如果要找的值比当前结点小，且当前结点的左子树为空，那么当前二叉树不会存在这个结点
        }else if (this.value > value && this.left != null){
            return this.left.findTarget(value);
        }else {
            return null;
        }
    }

    /**
     * 查找要删除结点targetNode的父结点
     * 递归查找
     * 如果当前结点的左结点或者右结点的值等于targetNode，那么当前结点就是targetNode的parent
     * @param targetNode
     * @return
     */
    public Node1 findParent(Node1 targetNode){
        if ((this.left != null && this.left.value == targetNode.value) ||
                (this.right != null && this.right.value == targetNode.value)){
            return this;
        }else if (targetNode.value < this.value && this.left != null){
            return this.left.findParent(targetNode);
        }else if (targetNode.value >= this.value && this.right != null){
            return this.right.findParent(targetNode);
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
        return "Node1{" +
                "value=" + value +
                '}';
    }
}
