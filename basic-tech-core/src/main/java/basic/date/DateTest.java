package basic.date;

/**
 * @Date 2020/6/1
 * @Author Frank Cooper
 * @Description
 */
public class DateTest {
    static DateTest handler = new DateTest();

    public static void main(String[] args) {
        handler.reshapeDuration(530L);
        handler.reshapeDuration(-4348L);
        handler.reshapeDuration(28995L);
        handler.reshapeDuration(34566621L);
        handler.reshapeDuration(566621L);

    }

    public String reshapeDuration(Long takeTime) {
        if (takeTime <= 0) return null;
        double tmp = takeTime / 1000.0;
        StringBuffer sb = new StringBuffer();
        int h = 0, m = 0;
        double s = 0.0;
        while (tmp >= 60.0 * 60.0) {
            tmp -= 60.0 * 60.0;
            h++;
        }
        if (h != 0) sb.append(String.format("%dh,", h));
        while (tmp >= 60.0) {
            tmp -= 60.0;
            m++;
        }
        if (m != 0) sb.append(String.format("%dm,", m));
        s = tmp;
        if (s != 0.0) sb.append(String.format("%.2fs", s));
        return sb.toString();
    }

}
