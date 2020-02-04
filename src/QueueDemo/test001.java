package QueueDemo;

import java.util.Scanner;

public class test001 {
    public static void main(String[] args) {
        CircleArrayQueue queue = new CircleArrayQueue(4);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit)：推出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中获取数据");
            System.out.println("h(head)：获取队列的头部数据");
            key = scanner.next().charAt(0);//接受字符
            switch (key) {
                case 's':
                    queue.showCircleArrayQueue();
                    break;
                case 'a':
                    try {
                        System.out.println("请输入一个数：");
                        int value = scanner.nextInt();
                        queue.addCircleArrayQueue(value);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println(queue.getCircleArrayQueue());
                    }catch (Exception e){
                            System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        queue.headCircleArrayQueue();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");
    }
}
