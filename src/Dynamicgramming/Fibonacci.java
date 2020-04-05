package Dynamicgramming;

public class Fibonacci {
    public static void main(String[] args) {
        int res = fib(164);
        System.out.println(res);
    }

    public static int fib(int n){
        int num_1 = 1;
        int num_2 = 1;
        int num_cur = num_1 + num_2;
        for (int i = 2; i < n; i++) {
            num_cur = num_1 + num_2;
            int tmp = num_cur;
            num_2 = num_1;
            num_1 = tmp;
        }
        return num_cur;
    }
}
