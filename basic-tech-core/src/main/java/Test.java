import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/12/2 12:39
 * @description:
 */
public class Test {


    static class _1st {
        public static void main(String args[]) {
            //请注意月份是从0-11,天数是1， 2013-1-1 至 2013-12-31
            Calendar start = Calendar.getInstance();
            start.set(2001, 11, 2); //2013-1-1 开始
            Calendar end = Calendar.getInstance();
            end.set(2099, 0, 0); // 2014--0-0结束，2014-1-1不算
            int sumSunday = 0;
            int sumSat = 0;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

            while (start.compareTo(end) <= 0) {
                int w = start.get(Calendar.DAY_OF_WEEK);
                if (w == Calendar.SUNDAY)
                    sumSunday++;
                if (w == Calendar.SATURDAY)
                    sumSunday++;
                //打印每天
                String curDate = format.format(start.getTime());
                if (test(curDate)) {
                    System.out.println(curDate);
                }

                //循环，每次天数加1
                start.set(Calendar.DATE, start.get(Calendar.DATE) + 1);
            }
            System.out.println("星期天总数为:" + sumSunday);
            System.out.println("星期六总数为:" + sumSunday);
        }


        /**
         * 筛选回文日期
         *
         * @param date
         * @return
         */
        private static boolean test(String date) {
            String regex = "^(.)(.)(.)(.)\\4\\3\\2\\1$";
            Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
            Matcher matcher = pattern.matcher(date);
            return matcher.matches();
        }
    }

}
