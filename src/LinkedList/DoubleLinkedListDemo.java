package LinkedList;

/**
 * 双向链表
 * 双向链表的节点中有两个指针域，next指向直接后继，prior指向直接前驱
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("******双向链表的测试******");
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 hero5 = new HeroNode2(5, "武松", "行者");
        DoubleLinkedList linkedList = new DoubleLinkedList();
        linkedList.add(hero1);
        linkedList.add(hero2);
        linkedList.add(hero3);
        linkedList.add(hero5);
        linkedList.add(hero4);
        System.out.println("\n双向链表创建完毕");
        linkedList.showNode(linkedList.getHead());

        System.out.println("\n------------更新链表节点--------------");
        linkedList.update(new HeroNode2(4,"林冲林冲","豹子头"));
        linkedList.showNode(linkedList.getHead());

        System.out.println("\n------------删除节点--------------");
        linkedList.delete(4);
        linkedList.showNode(linkedList.getHead());
    }
}

class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * 双向链表的遍历
     */
    public void showNode(HeroNode2 head){
        if (head.next == null){
            return;
        }
        HeroNode2 tmp = head.next;
        while (tmp != null){
            System.out.println(tmp.toString());
            tmp = tmp.next;
        }
    }
    /**
     * 添加元素到双向链表
     */
    public void add(HeroNode2 newNode){
        boolean isExist = false;
        HeroNode2 tmp = head;
        while (true){
            if (tmp.next == null){
                break;
            }else if (newNode.no < tmp.next.no){
                break;
            }else if (newNode.no == tmp.next.no){ //如果节点已存在就不添加
                isExist = true;
                break;
            }
            tmp = tmp.next;
        }
        if (isExist){
            System.out.println("节点已存在");
        }else {
            if (tmp.next != null){ // 如果添加的节点位于链表的最后，下面这句话会抛出空指针异常
                tmp.next.prior = newNode;
            }
            newNode.next = tmp.next;
            tmp.next = newNode;
            newNode.prior = tmp;
        }
    }
    /**
     * 更新节点
     * 用一个临时节点tmp遍历链表，如果tmp.no = node.no 那么就找到了要更新的节点，然后更新内容
     */
    public void update(HeroNode2 node){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 tmp = head;
        while (true){
            if (tmp.next == null){
                System.out.println("链表为空");
                break;
            }
            if (tmp.next.no == node.no ){
                tmp.next.name = node.name;
                tmp.next.nickname = node.nickname;
                break;
            }
            tmp = tmp.next;
        }
    }

    /**
     * 删除节点
     * @param no
     */
    public void delete(int no){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 tmp = head.next;
        boolean flag = false;
        while (true){
            if (tmp == null){
                break;
            }
            if (tmp.no == no){//找到了要删除的节点
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        if (flag){
            tmp.prior.next = tmp.next;
            if (tmp.next != null) {//如果删除的是最后一个节点，下面这句话有风险
                tmp.next.prior = tmp.prior;
            }
        }else {
            System.out.println("没有找到要删除的节点");
        }
    }
}

class HeroNode2{ // 创建节点对象
    public HeroNode2 prior;  //指向前一个节点

    //------数据域-------
    public int no; //写public只是不想写getter和setter
    public String name; //存储姓名
    public String nickname;

    public HeroNode2 next; //指向下一个节点


    public HeroNode2(int no, String name, String nickname) {
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
