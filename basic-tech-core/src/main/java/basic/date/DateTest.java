package basic.date;

import java.util.Calendar;

/**
 * @Date 2020/6/1
 * @Author Frank Cooper
 * @Description
 */
public class DateTest {
    static DateTest handler = new DateTest();

    public static void main(String[] args) {
//        handler.reshapeDuration(530L);
//        handler.reshapeDuration(-4348L);
//        handler.reshapeDuration(28995L);
//        handler.reshapeDuration(34566621L);
//        handler.reshapeDuration(566621L);
        handler.test();

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


    public void test() {
        Calendar now = Calendar.getInstance();
//一周第一天是否为星期天
        boolean isFirstSunday = (now.getFirstDayOfWeek() == Calendar.SUNDAY);
//获取周几
        int weekDay = now.get(Calendar.DAY_OF_WEEK);
//若一周第一天为星期天，则-1
        if (isFirstSunday) {
            weekDay = weekDay - 1;
            if (weekDay == 0) {
                weekDay = 7;
            }
        }
//打印周几
        System.out.println(weekDay);

//若当天为2014年10月13日（星期一），则打印输出：1
//若当天为2014年10月17日（星期五），则打印输出：5
//若当天为2014年10月19日（星期日），则打印输出：7
    }
}
