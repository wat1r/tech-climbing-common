package stackoverflow;

import java.util.Random;

public class _7PopularQuestion {

    static _7PopularQuestion handler = new _7PopularQuestion();

    public static void main(String[] args) {
        randomString(0);
    }


    public static String randomString(int i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 0)
                break;
            sb.append((char) ('`' + k));
        }
        return sb.toString();
    }
}
