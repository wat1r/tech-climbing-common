package quartz;

import org.quartz.CronExpression;

public class QuartzDemo {


    static QuartzDemo handler = new QuartzDemo();


    public static void main(String[] args) {
//        handler.validate("0 15 10 ? * *");
//        handler.validate("0 15 10 ? * 6#3");
        handler.validate("01 30 16 25 12 ? 2020");
    }


    private void validate(String expression) {
        boolean f = CronExpression.isValidExpression(expression);
        System.out.printf("[%s]--->%s\n",expression,f);
    }







}
