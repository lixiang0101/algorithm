package BiTree;

import java.util.ArrayList;
import java.util.LinkedList;

public class BinaryTree {
    public static void main(String[] args) {
        HeroNode h1 = new HeroNode(1,"宋江");
        HeroNode h2 = new HeroNode(2,"卢俊义");
        HeroNode h3 = new HeroNode(3,"吴用");
        HeroNode h4 = new HeroNode(4,"林冲");

        HeroBiTree biTree = new HeroBiTree();
        biTree.setT(h1);
        h1.setLeft(h3);
        h1.setRight(h2);
        h2.setRight(h4);
        System.out.println("二叉树的层次遍历：");
        biTree.levelTraverse();
        System.out.println("---------------------");
        biTree.postOrderTraverse();

        biTree.preOrderDelete(3);

        biTree.postOrderTraverse();


    }
}

/**
 * 定义一个二叉树，只需要定义一个root结点
 */
class HeroBiTree{
    private HeroNode t;

    public void setT(HeroNode t) {
        this.t = t;
    }

    public HeroNode getT() {
        return t;
    }

    // 二叉树的三种遍历方式
    public void preOrderTraverse(){
        if (this.t != null){
            this.t.preOrder();
        }
    }
    public void inOrderTraverse(){
        if (this.t != null){
            this.t.inOrder();
        }
    }
    public void postOrderTraverse(){
        if (this.t != null){
            this.t.postOrder();
        }
    }

    /**
     * 二叉树的水平层次遍历
     * 借助队列
     */
    public void levelTraverse(){
        LinkedList<HeroNode> queue = new LinkedList<>();
        queue.offer(t);
        while (!queue.isEmpty()){
            HeroNode tmp = queue.poll();
            System.out.println(tmp);
            if (tmp.getLeft() != null) {
                queue.offer(tmp.getLeft());
            }
            if (tmp.getRight() != null) {
                queue.offer(tmp.getRight());
            }
        }
    }

    public void preOrderSearch(int id){
        if (this.t != null){
            HeroNode heroNode = this.t.preOrderSearch(id);
            System.out.println(heroNode);
        }else {
            System.out.println("这是一个空树");
        }
    }

    public void inOrderSearch(int id){
        if (this.t != null){
            HeroNode heroNode = this.t.inOrderSearch(id);
            System.out.println(heroNode);
        }else {
            System.out.println("这是一个空树");
        }
    }

    public void postOrderSearch(int id){
        if (this.t != null){
            HeroNode heroNode = this.t.postOrderSearch(id);
            System.out.println(heroNode);
        }else {
            System.out.println("这是一个空树");
        }
    }
    public void preOrderDelete(int id){
        if (this.t == null){
            return;
        }
        if (this.t.getId() == id){
            this.t = null;
            System.out.println("编号" + id + "是树的根节点，此树已经被砍了");
        }else {
            this.t.preOrderDelete(id);
        }
    }

}

/**
 * 定义二叉链表的结点
 * 结点的定义：树中的一个单元，包含一个数据元素及若干指向其子树的分支。
 * 在节点中定义好遍历方式：DLR，LDR，LRD
 */
class HeroNode{
    private int id;
    private String name;
    private HeroNode left; // 左指针域
    private HeroNode right;// 右指针域

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public HeroNode getRight() {
        return right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    // 前序遍历 DLR
    public void preOrder(){
        System.out.println(this); // 这里不用判断当前节点是否为空，因为如果是空节点那么就不能调用方法
        if (this.left != null){
            left.preOrder();
        }
        if (this.right != null){
            right.preOrder();
        }
    }
    // 中序遍历 LDR
    public void inOrder(){
        if (this.left != null){
            left.inOrder();
        }
        System.out.println(this);
        if (this.right != null){
            right.inOrder();
        }
    }
    // 后序遍历 LRD
    public void postOrder(){
        if (this.left != null){
            left.postOrder();
        }
        if (this.right != null){
            right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode preOrderSearch(int id){

        if (this.id == id){
            return this; // 1、如果当前根节点是，那么就返回不继续往下查找了
        }

        // 如果根结点没有找到，那么就从左结点找
        HeroNode result = null;
        if (this.left != null) {
            result = left.preOrderSearch(id);
        }

        if (result != null){ // 如果左结点找到了就返回，否则就找右结点
            return result;
        }else if (this.right != null){
            result = right.preOrderSearch(id);
        }
        return result;
    }

    public HeroNode inOrderSearch(int id){
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.inOrderSearch(id);
        }
        if (result != null){
            return result;
        }
       if (this.getId() == id){
            return this;
        }
        if (this.right != null){
            result = this.right.inOrderSearch(id);
        }
        return result;
    }

    public HeroNode postOrderSearch(int id){
        HeroNode result = null;
        if (this.left != null) {
            result = this.left.postOrderSearch(id);
        }
        if (result != null){
            return result;
        }
        if (this.right != null){
            result = this.right.postOrderSearch(id);
        }
        System.out.println("后序查找");
        if (result == null && this.getId() == id){
            return this;
        }
        return result;
    }

    /**
     * 前向递归删除结点
     * 规定：
     * 1、如果是叶子结点就删除叶子节点
     * 2、如果是非叶子节点就删除子树
     * @param id
     */
    public void preOrderDelete(int id){
        boolean flag = false;
        if (this.left != null && this.left.id == id){
            this.left = null;
            flag = true;
        }else {
            this.left.preOrderSearch(id);
        }
        if (flag == false){
            if (this.right != null && this.right.id == id){
                this.right = null;
                flag = true;
            }else {
                this.right.preOrderDelete(id);
            }
        }
        if (flag == true){
            System.out.println("编号为" + id + "已删除");
        }
    }
}
