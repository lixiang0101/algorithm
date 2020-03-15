package Algorithm10;

public class BinarySearchNoRecur {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int index = search(arr, 2);
        System.out.println(index);
    }
    public static int search(int[] arr,int findValue){
        int low = 0;
        int high = arr.length - 1;
        while (low < high){
            int mid = (low + high) / 2;
            if (arr[mid] == findValue){
                return mid;
            }else if (arr[mid] < findValue){
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
