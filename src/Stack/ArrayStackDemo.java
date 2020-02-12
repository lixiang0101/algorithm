package Stack;

import java.util.Scanner;

/**
 * 用数组来模拟栈
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        System.out.println("*******测试栈*******");
        ArrayStack stack = new ArrayStack(6);

        boolean loop = true;
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (loop){
            System.out.println("s:显示栈的所有元素");
            System.out.println("e:退出当前程序");
            System.out.println("push:入栈");
            System.out.println("pop:出栈");
            key = scanner.next();
            switch (key) {
                case "s":
                    stack.show();
                    break;
                case "push":
                    System.out.println("请输入：");
                    int val = scanner.nextInt();
                    stack.push(val);
                    break;
                case "pop":
                    try {
                        System.out.println(stack.pop());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1; // 栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.stack = new int[maxSize];
    }

    public boolean isEmpty(){
        return top == -1;
    }
    public boolean isFull(){
        return top == maxSize - 1;
    }
    // push 入栈
    public void push(int num){
        if (isFull()){
            System.out.println("栈满～～～～～～～～");
            return;
        }
        top++;
        stack[top] = num;
    }
    // pop 出栈
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("空栈");
        }
        int result = stack[top];
        top--;
        return result;
    }
    // 遍历栈
    public void show(){
        if (isEmpty()){
            System.out.println("空栈");
            return;
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
}
