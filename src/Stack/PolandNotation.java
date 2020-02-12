package Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *  逆波兰表达式（后缀表达式）：从左往右扫描表达式，遇到数字时把数字压到栈低，遇到运算符时，
 *  弹出栈顶的两个数，然后用运算符对这两个数进行运算，把运算结果压到栈低；
 *  重复上面的过程，直到表达式的最后一个数。
 *
 *  (3+4)*5-6 => 3 4 + 5 * 6 -
 *
 *  思路：
 *  1、把逆波兰表达式中的数据和运算符依次放到一个ArrayList中，方便取出元素 - getListString
 *  2、初始化一个栈，从上面的ArrayList中依次取出元素
 *  3、判断：如果是数字，就入栈，否则就取出栈最上面的两个元素，和这个运算符进行运算，并把结果入栈
 */
public class PolandNotation {
    public static void main(String[] args) {
        // 定义一个逆波兰表达式，以空格分开
        String suffixExpression = "3 4 + 5 * 6 -";
        ArrayList<String> list = getListString(suffixExpression);
        System.out.println(list);
        System.out.println(calculate(list));

        String infixExpression = "(3+4)*5-6";
        System.out.println("中缀表达式转为列表："+toInfixExpressionList(infixExpression));
        List<String> suffixExpressionList = parseSuffixExpressionList(toInfixExpressionList(infixExpression));
        System.out.println("中缀表达式转为后缀表达式："+ suffixExpressionList);
    }

    public static ArrayList<String> getListString(String suffixExpression){
        String[] list = suffixExpression.split(" ");
        ArrayList<String> arrayList = new ArrayList<>();
        for (String s : list) {
            arrayList.add(s);
        }
        return arrayList;
    }

    public static int calculate(ArrayList<String> list){
        Stack<String> stack = new Stack<>();
        for (String s : list) {
            if (s.matches("\\d+")){ //如果是数字就入栈
                stack.push(s);
            }else { // 如果是运算符就取出栈最上面的两个数，并进行运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                switch (s) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                    default:
                        System.out.println("非法符号");
                        break;
                }
                // 把运算结果入栈
                stack.push(res + "");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    /**
     * 中缀表达式转后缀表达式：
     * 思路：
     *  1、初始化两个栈：运算符栈s1和存储中间结果栈s2（s2一直没有发生过弹栈，且最后结果是s2所有元素弹出后的逆序，所以可以用List）
     *  2、从左至右扫描中缀表达式；比如：1+（（2+3）*4）-5
     *  3、遇到数字是压入s2
     *  4、遇到操作符时：
     *      1）如果s1为空，或者s1栈顶元素为"（"，直接压入s1
     *      2）否则，若优先级比栈顶的优先级高，也压入s1
     *      3）否则，将s1栈顶的运算符弹出并压入到s2中，再次回到第1步
     *      所以当前的运算符到最后还是要压入到s1中
     *  5、遇到括号时：
     *      1）如果是左括号，直接压入s1
     *      2）如果是"）"，则依次弹出s1栈顶的运算符，并压入到s2，直到s1遇到"（"，此时将这一对括号丢掉
     *  6、重复步骤2-5，直到表达式的最右边
     *  7、将s1中剩余的运算符依次压入到s2中
     *  8、依次将s2中的元素弹出，结果的逆序，就是中缀表表达式转后缀表达式的结果
     */
    public static List<String> parseSuffixExpressionList(List<String> infixExpressionList){
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String s : infixExpressionList) {
            if (s.matches( "\\d+")){ // 执行 3
                s2.add(s);
            }else if (s.equals("(")){ // 执行 4.2
                s1.push(s);
            }else if (s.equals(")")){ //执行 5.2
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//丢掉
            }else { // 遇到符号
                while ( s1.size() != 0 && Operation.getValue(s) <= Operation.getValue(s1.peek())){
                    // 如果s的优先级比栈顶的优先级低，将s1栈顶的运算符弹出并压入到s2中，再次回到第1步
                    s2.add(s1.pop());
                }
                s1.push(s);
            }
        }
        while (s1.size() > 0 ){ // 第7步
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 把一个中缀表达式转为List，比如 （1+2）* 3 => [(,1,+,2,),*,3]
     * @param s 要转换的中缀表达式
     * @return 返回一个List
     * 思路：
     *  用一个索引i去扫描中缀表达式，如果是非数字，直接放到List中
     *  否则：
     *      要考虑多位数：
     *      初始化一个空字符串str，如果i扫描到的是数字，就拼接到str上，直到i扫描到非数字，然后把str放到List中
     * 问题：怎么判断一个字符串是不是数字呢？
     *  用ascii码，0 对应的 ascii码是 48，9 对应的是 57
     */
    public static List<String> toInfixExpressionList(String s){
        List<String> list = new ArrayList<>();
        int i = 0; //用来扫描表达式
        String str;
        char c; // 用来存放遍历到的字符
        do{
            c = s.charAt(i);
            if ( c < 48 || c > 57){
                list.add("" + c); // "" + c 是把c转为字符串
                i++;
            }else {
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;
                    i++;
                }
                list.add(str);
            }
        }while (i < s.length());
        return list;
    }
}

/**
 * 定义一个判断符号优先级的类
 */
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    public static int getValue(String key){
        int result = 0;
        switch (key){
            case "+":
               result = ADD;
               break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("非法符号");
                break;
        }
        return result;
    }
}
