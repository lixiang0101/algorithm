package HashTable;

/**
 * 存储员工信息hash表
 * 1、每个员工都有一个唯一的id，根据员工id用"除模留余法"计算hash值
 * 2、员工类，即链表的单个节点
 * 3、员工链表，组成数组的元素
 * 4、数组，hashTable
 */

public class HashTableDemo {
    public static void main(String[] args) {
        // 1、创建HashTable
        HashTable hashTable = new HashTable(7);
        // 1、添加员工
        for (int i = 1; i < 15; i++){
            String name = "user" + i;
            hashTable.add(new Employee(i, name));
        }
        //hashTable.list();
        //hashTable.find(996);
        hashTable.delete(1);
        hashTable.list();
    }

}

class HashTable{
    private EmployeeLinkedList[] employeeLinkedListArray;
    private int size;

    public HashTable(int size) {
        this.size = size;
        employeeLinkedListArray = new EmployeeLinkedList[size];
        // 上面只是创建了一个空列表，里面的每一个元素都是null，并是EmployeeLinkedList
        for (int i = 0; i < size; i++) {
            employeeLinkedListArray[i] = new EmployeeLinkedList();
        }
        // 现在数组里面装的才是并是EmployeeLinkedList对象，才可以执行下面的 add方法
    }

    /**
     * 添加Employee到HashTable中
     * 1、先计算hash值，用hash值找到添加到哪条链表上
     * 2、调用链表的add方法
     * @param employee
     */
    public void add(Employee employee){
        int key = key(employee.getId());
        employeeLinkedListArray[key].add(employee);
    }

    public void delete(int id){
        int key = key(id);
        employeeLinkedListArray[key].delete(id);
    }

    /**
     * 根据id计算hash值，取模留余法
     * @param id Employee的id
     * @return 返回hash值
     */
    public int key(int id){
        return id % size;
    }
    public void list(){
        for (int i = 0; i < size; i++) {
            System.out.printf("第%d条链表：",i);
            employeeLinkedListArray[i].list();
            System.out.println();
        }
    }
    public void find(int id){
        int key = key(id);
        Employee employee = employeeLinkedListArray[key].findEmpByID(id);
        if (employee == null){
            System.out.println("没有此员工");
        }else {
            System.out.println("在第"+ key + "条链表找到此员工 " + employee.toString());
        }
    }
}

class EmployeeLinkedList{
    private Employee head; // head默认为空

    public void add(Employee employee){
        if (head == null){
            head = employee;
            return;
        }
        Employee tmp = head;
        while (tmp.getNext() != null){
            tmp = tmp.getNext();
        }
        tmp.setNext(employee);
    }
    public void list(){
        if (head == null){
            System.out.println("此链表当前没有雇员");
            return;
        }
        Employee tmp = head;
        while (tmp != null){
            System.out.print(tmp.toString() + "    ");
            tmp = tmp.getNext();
        }
    }
    public Employee findEmpByID(int id){
        if (head == null){
            System.out.println("链表为空");
            return null;
        }
        Employee tmp = head;
        while (tmp != null){
            if (tmp.getId() == id){ // 判断当前节点是不是要找的节点
                break;
            }
            tmp = tmp.getNext();
        }
        return tmp;
    }
    public void delete(int id){
        if (head == null){
            System.out.println("此ID员工不存在");
            return;
        }
        if (head.getId() == id){
            head = head.getNext();
            System.out.println("此员工已删除");
        }
        Employee tmp = head;
        while (tmp.getNext() != null){
            if (tmp.getNext().getId() == id){
                tmp.setNext(tmp.getNext().getNext());
                System.out.println("此员工已删除");
            }
            tmp = tmp.getNext();
        }
    }
}

class Employee {
    private int id;
    private String name;
    private Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNext(Employee next) {
        this.next = next;
    }

    public Employee getNext() {
        return next;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
