package quartz;

import org.quartz.CronExpression;

public class QuartzDemo {


    static QuartzDemo handler = new QuartzDemo();


    public static void main(String[] args) {
        handler.validate("0 15 10 ? * *");
        handler.validate("0 15 10 ? * 6#3");
    }


    private void validate(String expression) {
        boolean f = CronExpression.isValidExpression(expression);
        System.out.printf("[%s]--->%s\n",expression,f);
    }


}
