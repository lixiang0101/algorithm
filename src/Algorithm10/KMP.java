package Algorithm10;

public class KMP {

    public static void main(String[] args) {
        String str = "ABABAC";
        String subStr = "AC";
        int i = violenceMath(str, subStr);
        System.out.println(i);
    }

    public static int violenceMath(String str,String subStr){
        char[] strArr = str.toCharArray();
        char[] subStrArr = subStr.toCharArray();
        int i = 0;
        int j = 0;
        int k = i;
        while (i < strArr.length && j < subStrArr.length){
            if (strArr[i] == subStrArr[j]){
                ++i;
                ++j;
            }else {
                j = 0;
                i = ++k;
            }
        }
        if (j >= subStrArr.length){
            return k;
        }else {
            return 0;
        }
    }
}
