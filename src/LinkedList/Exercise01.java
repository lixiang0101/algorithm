package LinkedList;

/**
 * 单链表的反转
 */

public class Exercise01 {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4};
        ListNode listNode = new ListNode();
        for (int i : arr) {
            listNode.add(new Node(i));
        }
        listNode.show();
        System.out.println("\n链表反转");
        Node head = solution(listNode.getHead());
        listNode.setHead(head);
        listNode.show();

        System.out.println("\n单链表每K个反转");
        // -- 单链表没k个反转
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        Node t = n1;
        while (t != null){
            System.out.print(t);
            t = t.next;
        }
        System.out.println();

    }

    /**
     * 单链表的反转就是结点之间箭头的反转
     * @param head
     * @return
     */
    public static Node solution(Node head){
        Node pre = null; // 注意：初始化pre应该为null，因为链表的最后一个元素指向的就是null
        Node cur = head.next;
        while (cur != null){
            Node next = cur.next; // 用一个临时变量保存当前结点的下一个结点
            cur.next = pre; // 当前结点指向前面的结点，当前结点反转
            pre = cur; // 当前结点变成pre结点
            cur = next; // 往下移，继续反转下一个结点
        }
        head.next = pre; // cur为null是while循环结束，pre就是原链表的最后一个结点，也就是新链表的第一个结点
        return head;
    }




}

class Node{
    int value;
    Node next;
    public Node(){
    }
    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "->";
    }
}

class ListNode{
    private Node head = new Node();

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void add(Node node){
        if (head.next == null){
            head.next = node;
            return;
        }
        Node tmp = head;
        while (tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = node;
    }

    public void show(){
        if (head.next == null){
            throw new RuntimeException("这是一颗空树");
        }
        Node tmp = head.next;
        while (true){
            if (tmp == null){
                break;
            }
            System.out.print(tmp);
            tmp = tmp.next;
        }
    }
}
