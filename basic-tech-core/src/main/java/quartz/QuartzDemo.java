package quartz;

import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

public class QuartzDemo {


    static QuartzDemo handler = new QuartzDemo();


    public static void main(String[] args) throws ParseException {
//        handler.validate("0 15 10 ? * *");
//        handler.validate("0 15 10 ? * 6#3");
//        handler.validate("01 30 16 25 12 ? 2020");
//        handler.validate("0/15 5-10 9,18 1,15 * ? 2018-2023");
//        handler.validate("*/5 * * * * ?");
        handler.validate("00 00 08 ? * 1");
//
//        HashMap<String,String>map = new HashMap<>();
//        map.put("a","1");
//        map.put("b","2");
//        map.put("c","3");
//
//        String json = JSON.toJSONString(map);//map转String
//        JSONObject jsonObject = JSON.parseObject(json);//String转json
//
////json转map
//        Map<String, String> jsonMap = JSONObject.toJavaObject(jsonObject, Map.class);
//        //String转map
//        Map<String, String> jsonMap1 = JSONObject.parseObject(json, Map.class);
    }

    /**
     * https://www.jianshu.com/p/fb92bdf7a093
     * // [0, 15, 30, 45]
     * protected transient TreeSet<Integer> seconds;
     * // [5, 6, 7, 8, 9, 10]
     * protected transient TreeSet<Integer> minutes;
     * // [9, 18]
     * protected transient TreeSet<Integer> hours;
     * // [1, 15]
     * protected transient TreeSet<Integer> daysOfMonth;
     * // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 99]
     * // 99代表特殊字符*，此处是作为一个标记元素
     * protected transient TreeSet<Integer> months;
     * // [98]
     * // 98代表特殊字符?，此处是作为一个标记元素
     * protected transient TreeSet<Integer> daysOfWeek;
     * // [2018, 2019, 2020, 2021, 2022, 2023]
     * protected transient TreeSet<Integer> years;
     *
     * @param expression
     * @throws ParseException
     */

    private void validate(String expression) throws ParseException {
        boolean f = CronExpression.isValidExpression(expression);


        CronExpression cronExpression = new CronExpression(expression);

        Date first = cronExpression.getTimeAfter(new Date());
        Date second = cronExpression.getTimeAfter(first);
        System.out.printf("[%s]--->%s\n", expression, f);
    }


    private void testMonSun() {
        String expression = "";

    }


}
