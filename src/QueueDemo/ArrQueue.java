package QueueDemo;

/**
 * 基于数组实现的单向的队列，又一个问题就说队列不能复用
 */

class ArrQueue{
    private int maxSize; //数组的最大容量
    private int front; //指向队列的头
    private int rear; //指向队列的尾
    private int[] arr; //用数组来模拟队列

    // 创建队列构造器，传入参数指定最大容量
    public ArrQueue(int maxSize){
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.rear = -1;
    }

    // 判断队列是否已满，如果rear位于队列尾，那么队列已满
    public boolean isFull(){
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    // 添加数据到队列
    public void addArrQueue(int num){
        if (isFull()){
            System.out.println("队列已满，不能添加");
            return;
        }
        arr[++rear] = num;
    }
    // 获取数据
    public int getArrQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        return arr[++front];
    }
    // 显示队列的所有数据
    public void showArrQueue(){
        if (isEmpty()){
            System.out.println("队列为空");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    // 显示队列头部数据
    public void headArrQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        System.out.println(arr[front+1]);
    }
}
