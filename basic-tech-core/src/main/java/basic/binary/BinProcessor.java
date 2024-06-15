package basic.binary;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/05/27/ 14:30
 * @description
 */
public class BinProcessor {

    public static void main(String[] args) {
        int maxx = 199;
        for (int i = 1; i <= maxx; i++) {
            process(i, 8);
        }
    }


    public static void process(int src, int bit) {
        String r = Integer.toBinaryString(src);
        if (r.length() > bit) {
            System.out.println(String.format("%s\t%d\t%s\t%s", r, src, "-", "-"));
            return;
        }
        if (r.length() < bit) {
            String add = StringUtils.repeat('0', bit - r.length());
            r = add + r;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < r.length(); i++) {
            sb.append(r.charAt(i) == '1' ? '0' : '1');
        }
        String dest = sb.reverse().toString();
        int res = Integer.parseInt(dest, 2);

        System.out.println(String.format("%d\t%s\t%s\t%d", src, r, dest, res));
    }


    public static int reverseBits02(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) + (n & 1);
            n >>= 1;
        }

        return res;
    }


}
