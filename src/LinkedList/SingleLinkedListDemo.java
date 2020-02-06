package LinkedList;

import java.util.Stack;

public class SingleLinkedListDemo{
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(0, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2 ,"吴用", "智多星");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(node1);
        singleLinkedList.addNode(node2);
        //singleLinkedList.showNode();

        // 测试按顺序添加
        HeroNode node3 = new HeroNode(1,"卢俊义","玉麒麟");
        singleLinkedList.addNodeByOrder(node3);
        //singleLinkedList.showNode();

        // 测试修改node
        HeroNode node4 = new HeroNode(1,"卢俊义1","玉麒麟");
        singleLinkedList.update(node4);
        singleLinkedList.showNode();

        // 测试删除节点
        System.out.println("测试删除节点");
        singleLinkedList.delete(4);
        singleLinkedList.showNode();
        System.out.println("链表的长度为："+singleLinkedList.getLength(singleLinkedList.getHead()));

        System.out.println("获取倒数第1个节点");
        System.out.println(SingleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 3).toString());

        System.out.println("--------------反转单向链表-------------------");
        SingleLinkedList.reverse(singleLinkedList.getHead());
        singleLinkedList.showNode();

        System.out.println("\n--------------方向遍历单向链表-------------------");
        SingleLinkedList.showReverse(singleLinkedList.getHead());
    }
}


class SingleLinkedList { // 定义单链表类
    // 创建头节点，头节点指向单链表的第一个节点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    // 添加到新节点到单链表上
    public void addNode(HeroNode node){
        // 因为head节点不能动，所以需要一个临时节点
        HeroNode tmp = head;
        while (true){
            if (tmp.next == null){
                break;
            }
            tmp = tmp.next;
        }
        tmp.next = node;
    }
    public void showNode(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head.next;
        while (true){
            if (tmp == null){
                break;
            }
            System.out.println(tmp.toString());
            tmp = tmp.next;
        }
    }

    // 按照节点的编号来添加，如果编号已存在，则不添加
    public void addNodeByOrder(HeroNode node){
        HeroNode tmp = head;
        boolean isExist = false; // 用于标记节点是否存在，因为节点已经存在的话就不添加
        while (true){// 遍历整个链表
            if (tmp.next == null){ // 说明链表为空
                break;
            }else if ( tmp.next.no > node.no ){ // 找到node应该插入的位置，在tmp的后面
                break;
            }else if (tmp.next.no == node.no){ // 如果编号已经存在，则不添加
                isExist = true;
                break;
            }
            tmp = tmp.next; //
        }

        if (isExist){
            System.out.println("要添加的节点已经存在，不能添加");
        }else {
            node.next = tmp.next;
            tmp.next = node;
        }
    }
    // 更新节点数据
    public void update(HeroNode node){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head;
        while (true){
            if (tmp.next == null){ //链表遍历结束
                break;
            }
            if (tmp.next.no == node.no ){
                node.next = tmp.next.next;
                tmp.next = node;
                break;
            }
            tmp = tmp.next;
        }
    }
    //删除节点
    //1、先找到要删除的节点的前一个节点 tmp
    //2、让tmp指向tmp.next.next
    //3、被删除的节点没有引用指向它，就会被垃圾回收机制回收
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode tmp = head;
        boolean flag = false;
        while (true){
            if (tmp.next == null){ //如果没有找到要删除的节点
                System.out.println("链表遍历结束");
                break;
            }else if(tmp.next.no == no){
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag){ // 找到了要删除的节点
            tmp.next = tmp.next.next;
        }else {
            System.out.println("没有找到要删除的节点");
        }
    }
    // 获取单链表长度，不计算头节点
    // 首先判断链表是不是为空
    // 然后用临时变量往下遍历，如果tmp != null，长度加1
    public static int getLength(HeroNode head){
        if (head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode tmp = head.next;
        while (tmp != null){
            length ++;
            tmp = tmp.next;
        }
        return length;
    }

    //查找单链表中的倒数第k个节点【新浪面试题】
    // 1、判断k是否合法
    // 2、判断链表是否为空
    // 3、获取链表长度
    // 4、遍历链表，遍历到（length - k）位置即为倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode headNode,int index){
        if (headNode.next == null ){
            //throw new RuntimeException("链表为空");
            return null;
        }
        int length = SingleLinkedList.getLength(headNode);
        HeroNode tmp = headNode;
        if (index > length || index < 0){
            throw new RuntimeException("超过链表范围");
        }
        for (int i = 0; i < (length - index ); i++) {
            tmp = tmp.next;
        }
        return tmp.next;
    }

    /**
     * 单向链表的反转【腾讯面试题】
     * 1、定义一个临时头节点 reverseHead，
     * 2、依次遍历链表的每个节点cur，每次都把cur直接插入到reverseHead的后面
     * 主要思路就是上面两步
     * 3、定义一个临时节点next，用来指向cur的下一个节点
     * 4、把cur插入到reverseHead后面后，cur可以通过next找到它原来的下一个节点，这样就可以继续遍历
     * @param headNode
     */
    public static void reverse(HeroNode headNode){
        if (headNode.next == null || headNode.next.next == null){//如果链表为空，或者链表只有一个节点就不反转
            return;
        }
        HeroNode cur = headNode.next; // 用于指向当前节点
        HeroNode reverseHead = new HeroNode(0,"","");//定义一个头节点，作为反转后链表的临时头节点
        HeroNode next = null;// 指向当前节点的下一个节点

        while (cur != null){ //如果当前节点不为null，就继续往下遍历
            next = cur.next; // 先暂时保留当前节点的下一个节点
            cur.next = reverseHead.next; // 把当前这个节点的next指向reverseHead节点后面的链表
            reverseHead.next = cur; // 让reverseHead指向cur
            cur = next;// 继续往下遍历
        }
        headNode.next = reverseHead.next; // 把reverseHead换成headNode指向
    }
    /**
     * 反向遍历链表，用栈的方式
     * 先从头遍历把节点压入栈，然后再取出
     * 也可以先反转链表，然后再遍历，但这样会破坏链表的结构，不推荐
     */
    public static void showReverse(HeroNode head){
        if (head.next == null ){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null){
            stack.push(cur); //当前节点压入到栈中
            cur = cur.next;  //后移一位
        }

        while (stack.size()>0){ // 弹出
            System.out.println(stack.pop());
        }
    }
}


class HeroNode{ // 创建节点对象
    public int no; //写public只是不想写getter和setter
    public String name;//存储姓名
    public String nickname;
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}
