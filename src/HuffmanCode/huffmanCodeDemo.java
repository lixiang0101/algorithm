package HuffmanCode;

import java.util.*;

public class huffmanCodeDemo {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] content2Bytes = content.getBytes();

        byte[] huffmanZip = huffmanZip(content2Bytes);
        System.out.println(Arrays.toString(huffmanZip));

        System.out.println("原字节数组长度为：" + content2Bytes.length +
                "\n压缩后哈夫曼编码字节数组长度为：" + huffmanZip.length);

        byte[] decodeBytes = decode(huffmanCode, huffmanZip);
        System.out.println(new String(decodeBytes));
    }

    /**
     * 获取一个byte类型的数的反码
     * 这是一个经过哈夫曼编码后得到的byte数组，将每个数转为int后，获取反码，截取后8位
     * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @param b
     * @param flag 如果是数组的最后一个数就不与256进行位或运算，因为最后一个数不需要补位，原因看zip方法
     * Integer.toBinaryString(int a) 获取a的反码
     */
    public static String byte2BitString(boolean flag,byte b){
        int tmp = b; // byte强转位int
        if (flag) {
            tmp |= 256;  // 整数的反码是原码，比如，1的原码是1，不够8位，与256做位或运算是为了补0：
            // 1 00000000 | 00000001 = 1 00000001
        }
        String s = Integer.toBinaryString(tmp); // 获取int的反码
        if (flag){
            return s.substring(s.length() - 8); // 截取反码后8位
        }else {
            return s;
        }
    }

    /**
     *
     * @param huffmanCode {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
     * @param huffmanBytes [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
     * @return
     */
    public static byte[] decode(Map<Byte,String> huffmanCode,byte[] huffmanBytes){
        // 1、把huffmanBytes里的每个byte转为0、1字符串，并拼接起来
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0;i < huffmanBytes.length;i++){
            boolean flag = (i == huffmanBytes.length - 1); // 判断是否是处理最后一个数
            String s = byte2BitString(!flag, huffmanBytes[i]);
            stringBuilder.append(s);
        }
        System.out.println(stringBuilder);
        // 2、调换huffmanCode的k、v
        HashMap<String, Byte> huffmanCode1 = new HashMap<>();
        for (Map.Entry<Byte,String> entry:huffmanCode.entrySet()){
            huffmanCode1.put(entry.getValue(),entry.getKey());
        }

        String tmp;
        ArrayList<Byte> list = new ArrayList<>();
        for (int i = 0;i < stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b;
            while (flag) {
                tmp = stringBuilder.substring(i, i + count); // 截取i到i+count之间的0、1
                b = huffmanCode1.get(tmp);
                if (b != null) { // 如果当前取到的这段0、1在哈夫曼编码表里，那么就找到了一个字符
                    list.add(b);
                    i += count; //因为上面截取的时候不包含 i+count位置的，所以新的i从i+count开始
                    flag = false; // 进入下一轮的扫描，count又变成了1
                }else {
                    count ++;
                }
            }
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length;i++){
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 对哈夫曼压缩过程进行封装
     * @param bytes
     * @return
     */
    public static byte[] huffmanZip(byte[] bytes){
        // 把字节数组封装为字节结点
        List<Node> nodes = getNodes(bytes);
        // 生成哈夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        // 把哈夫曼树的每个叶子结点 转为 哈夫曼编码
        Map<Byte, String> code = getHuffmanCode(huffmanTree);
        // 对字节数组进行压缩
        byte[] zip = zip(bytes, code);
        return zip;
    }
    /**
     * 把一段字节数组里的每个字节通过哈夫曼编码，得到一个哈夫曼编码长字符串，把这个字符串转为byte数组
     * @param bytes 字符串对应的字节数组
     * @param huffmanCode
     * @return
     * 怎么把一个只包含0、1的字符串转为一个字节数组(byte[])呢？
     * 1、对Integer.parseInt(s,2)说明：
     * 把一个只包含0、1的字符串s，按照二进制转成一个int，比如：Integer.parseInt("10101000",2) 结果是128+32+8=168
     * 2、一个byte是8个二进制位，从s的头开始每次截取8个长度，用1方法转为int
     * 3、将2得到的int强转为byte类型，强转说明：
     *  int是32位，byte是8位，强转只会保留int的最后8位，截取的8位在计算机中是补码
     *  存入到byte[]里的是一个个数，而不是一个个8位二进制
     *  所以会把补码转为原码，然后换算成数？
     *  补码转原码：先减1，然后除符号位（第1位）其他位都取反（0变1，1变0）
     *  比如：10101000 - 1 = 10100111 再取反得到原码是：11011000 对应的数为：-88
     * 总结：截取的8个0、1计算机按二进制计算成int后，再强转成byte，计算机返回给人类的数是这个byte的反码按二进制计算出来的数
     *   8位0、1 --> int -- > byte
     * 那怎么把这个数给转回去呢，获取原来的8个0、1呢？
     *  先转成int，在用Integer.toBinaryString(int a) 获取转为int后的反码，最后截取反码的最后8位
     *  byte --> int --> 8位0、1
     */
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCode){

        //2、把上面字节数组中的每个字节从huffmanCode中找到对应的哈夫曼编码，并追加到codeStringBuilder
        StringBuilder codeStringBuilder = new StringBuilder(); //编码字符串
        for (byte b : bytes) {
            codeStringBuilder.append(huffmanCode.get(b));
        }

        //3、把codeStringBuilder转为byte数组
        // 比如 编码字符串是："101111000"，从第一位开始，每8个转成一个二进制数，存到byte数组中

        // 那么要先创建一个byte数组，所以需要先更加编码字符串算出数组长度
        int length = 0;
        //4、求byte数组的长度，如果编码字符串长度正好是8的倍数，那么就直接是长度除8，否则长度除8+1
        // 或者 (codeStringBuilder.length() + 7 ) / 8
        if (codeStringBuilder.length() % 8 == 0){
            length = codeStringBuilder.length() / 8;
        }else {
            length = codeStringBuilder.length() / 8 + 1;
        }
        byte[] code2byte = new byte[length];
        int index = 0;
        for (int i = 0; i < codeStringBuilder.length(); i += 8){
            int tmp;
            if (i + 8 > codeStringBuilder.length()){ // 如果最后一次取的的长度不足8，那么就取所有
                // substring只指定开始位置，不指定截取长度就会直接从开始位置截取到最后
                tmp = Integer.parseInt(codeStringBuilder.substring(i),2);
            }else {
                tmp = Integer.parseInt(codeStringBuilder.substring(i,i+8),2);
            }
            code2byte[index++] = (byte) tmp;
        }
        return code2byte;
    }
    /**
     * 封装一下getHuffmanCode方法，调用更方便一些
     * @param root
     * @return
     */
    public static Map<Byte,String> getHuffmanCode(Node root){
        if (root == null){
            return null;
        }else {
            getHuffmanCode(root.leftChild,"0",stringBuilder);
            getHuffmanCode(root.rightChild,"1",stringBuilder);
        }
        return huffmanCode;
    }

    // 用一个字典来存放霍夫曼编码
    static Map<Byte,String> huffmanCode = new HashMap<Byte, String>();
    //
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 生成霍夫曼编码字典 key 是字节对应的byte值，value 是从root结点到该字节所在叶子结点路径对应的二进制串，也就是哈夫曼编码
     * @param node 根结点
     * @param code 树中每个左分支赋予0，右分支赋予1
     * @param stringBuilder
     * @return
     */
    public static Map<Byte,String> getHuffmanCode(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null){
            if (node.data == null){ // 如果当前结点的data是null，那么这个结点是一个根结点
                if (node.leftChild != null){ // 递归左子树，给编码增加一个1
                    getHuffmanCode(node.leftChild,"0",stringBuilder2);
                }
                if (node.rightChild != null){// 递归右子树，给编码增加一个0
                    getHuffmanCode(node.rightChild,"1",stringBuilder2);
                }
            }else {// 如果当前结点是叶子结点，编码完成
                huffmanCode.put(node.data,stringBuilder2.toString());
            }
        }
        return huffmanCode;
    }

    /**
     * 生成霍夫曼树
     * @param nodes 存放字符结点的List
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node newNode = new Node(null,leftNode.weight + rightNode.weight);
            newNode.leftChild = leftNode;
            newNode.rightChild = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    /**
     * 统计字节数组里每个字节的次数，次数也就是权重，然后封装成Node
     * @param arr 字节数组
     * @return
     */
    public static List<Node> getNodes(byte[] arr){
        ArrayList<Node> nodes = new ArrayList<>();

        // 统计每个字符出现的此时
        Map<Byte, Integer> map = new HashMap<>();
        for (byte b : arr) {
            Integer count = map.getOrDefault(b, 0) + 1;
            map.put(b,count);
        }

        for (Map.Entry<Byte,Integer> entry:map.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        };

        return nodes;
    }

}

class Node implements Comparable<Node>{
    public Byte data;
    public int weight;
    public Node leftChild;
    public Node rightChild;

    public Node(Byte data, int value) {
        this.data = data;
        this.weight = value;
    }

    public void preOrderTraverse(){
        System.out.println(this);
        if (this.leftChild != null){
            this.leftChild.preOrderTraverse();
        }
        if (this.rightChild != null){
            this.rightChild.preOrderTraverse();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", value=" + weight +
                '}';
    }
}

