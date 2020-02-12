package LinkedList;

/**
 * 约瑟夫问题
 */

public class JosepfuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList c = new CircleSingleLinkedList();
        c.add(5);
        c.show();
        c.countBoy(1,2,5);
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }
}

/**
 * 单向环形链表
 */
class CircleSingleLinkedList{
    private Boy first = null;

    /**
     * 指定小孩个数，构造一个环形的链表
     * @param nums 要添加几个小孩到循环链表中
     */
    public void add(int nums){
        if (nums < 1){
            System.out.println("参数非法");
            return;
        }
        Boy cur = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1){ // 用first固定第一个小孩
                first = boy;
                first.setNext(first);// 构成换形
            }else {
                cur.setNext(boy);
                boy.setNext(first); // 构成换形
            }
            cur = boy; //cur往下移动到当前最新节点这里
        }
    }

    /**
     * 链表的遍历：用一个临时节点cur来遍历，判断cur的下一个节点是否是first节点，如果是那么遍历结束
     *  cur = cur.getNext() 移动cur到下一个节点
     */
    public void show(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true){
            System.out.println(cur.getNo());
            if (cur.getNext() == first){ // 遍历结束条件
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * 小孩出圈方法
     * @param startNo 开始数数的节点位置
     * @param m 数几个数
     * @param nums 链表中节点的个数
     * 怎么把一个小孩（节点）从环形链表中删除？
     *    这个小孩的前一个节点不再指向他，而且他也不指向任何的节点，那么就会被垃圾回收器回收
     *    这就需要一个临时节点helper，始终指向当前节点的前一个节点
     *    需要一个临时节点first，指向这个小孩，把这个小孩的编号打印出来后，把first从当前节点往后移动一位
     *    然后让helper指向他
     * 所以当要把first这个节点删除时，只需要让
     *             helper.next = first.next;
     *             first = first.next;
     * 当helper == first 时，说明当前链表中只有一个节点
     */
    public void countBoy(int startNo,int m,int nums){
        //对参数的合法性进行判断
        if (nums < 1 || startNo > nums || m < 0 || first == null){
            System.out.println("参数不合法，请检查输入的参数");
            return;
        }
        // 创建临时节点helper，并指向first的前一个节点
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        // 让first和helper同时往后移动 m - 1
        while (true) {
            if (helper == first){ // 此时链表里只剩下一个节点
                break;
            }
            for (int i = 1; i < m; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("\n此次出队列的小孩编号为：%d",first.getNo());
            // 把 first 从当前节点往后移动一个节点，然后删除当前这个节点
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("\n最后留在队列中的小孩编号为：%d",first.getNo());
    }
}