package QueueDemo;

/**
 * 基于数组实现的环形队列
 * 1、front和rear对maxSize取模来实现位置的循环变动
 * 2、这里的这种写法把队列的最后一个位置预留出来里，也就是说如果指定的maxSize=4，那么队列的实际可以使用的长度为3
 */

class CircleArrayQueue {
    private int maxSize; //数组的最大容量
    private int front; //指向队列的头
    private int rear; //指向队列最后一个元素的后一个位置，因为希望空出一个空间做约定，所以rear的初始值为0
    private int[] arr; //用数组来模拟队列

    // 创建队列构造器，传入参数指定最大容量
    public CircleArrayQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
    }

    // 判断队列是否已满，如果rear位于队列尾，那么队列已满
    public boolean isFull(){
        return ( rear + 1 ) % maxSize == front;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 添加数据到队列
    public void addCircleArrayQueue(int num){
        if (isFull()){
            System.out.println("队列已满，不能添加");
            return;
        }
        // rear指向的数组的最后一个元素的后一个位置，所以直接arr[rear]添加
        arr[rear] = num;
        //rear + 1 是把rear往后移动，因为是环形队列，所以取模maxSize，避免数组越界
        rear = (rear + 1) % maxSize;
    }
    // 获取数据
    public int getCircleArrayQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        int value = arr[front];
        front = ( front + 1 ) % maxSize;
        return value;
    }
    // 显示队列的所有数据
    public void showCircleArrayQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
            return;
        }
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }
    // 当前队列的数据个数
    public int size(){
        if (isEmpty()){
            return 0;
        }
        return ( rear + maxSize - front ) % maxSize;
    }

    // 显示队列头部数据
    public void headCircleArrayQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        System.out.println(arr[front]);
    }
}
