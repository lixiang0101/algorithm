package LinkedList;

public class SingleLinkedListDemo{
    public static void main(String[] args) {
        HeroNode node1 = new HeroNode(0, "宋江", "及时雨");
        HeroNode node2 = new HeroNode(2 ,"吴用", "智多星");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(node1);
        singleLinkedList.addNode(node2);
        //singleLinkedList.showNode();

        HeroNode node3 = new HeroNode(1,"卢俊义","玉麒麟");
        singleLinkedList.addNodeByOrder(node3);
        singleLinkedList.showNode();
    }
}


class SingleLinkedList { // 定义单链表类
    // 创建头节点，头节点指向单链表的第一个节点
    private HeroNode head = new HeroNode(0,"","");

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
