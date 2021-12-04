package interview.alibaba.question.one;



public class One {


    /**
     * 1、现输入一个字符串，对字符串进行压缩，如：如字符串"abcdabcd"由于无连续重复字符，压缩后的字符串还是"abcdabcd"，字符串"xxxrryyyyyyz"压缩后就成为"3x2r6yz"。
     *
     * @param args
     */
    public static void main(String[] args) {
        One handler = new One();
        System.out.printf("%s\n", handler.process("abcdabcd"));
        System.out.printf("%s\n", handler.process("xxxrryyyyyyz"));
        System.out.printf("%s\n", handler.process("xxxrryyyyyyy"));

    }

    public String process(String src) {
        char[] chs = src.toCharArray();
        StringBuilder res = new StringBuilder();
        int count = 1;
        int slow = 0, fast = 1;
        while (fast < chs.length) {
            while (fast < chs.length && chs[fast] == chs[slow]) {
                fast++;
                count++;
            }
            res.append(count == 1 ? "" : count).append(chs[slow]);
            slow = fast;
            fast++;
            count = 1;
        }
        if (slow < chs.length) res.append(count == 1 ? "" : count).append(chs[slow]);
//        res.append(count).append()
        return res.toString();
    }


}
