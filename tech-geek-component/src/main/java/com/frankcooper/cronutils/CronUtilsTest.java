package com.frankcooper.cronutils;

import com.cronutils.*;
import com.cronutils.descriptor.CronDescriptor;
import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;

import java.util.Date;
import java.util.Locale;

import static com.cronutils.model.CronType.QUARTZ;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/30 14:29
 * @description:
 */
public class CronUtilsTest {


    public static void main(String[] args) {
//        testOne();
    }


    public static void testOne() {
        String expression = "0 0 0 ? 5 2#1";
        //expression = "0 0 0 0 5 2#1";
        //格式校验
        boolean b = checkValid(expression);
        System.out.println(b);

        if (b) {
            //解释cron表达式
            String s = describeCron(expression);
            System.out.println(s);
        }


    }

    /**
     * 解释cron表达式
     */
    public static String describeCron(String expression) {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        CronParser parser = new CronParser(cronDefinition);
        Cron cron = parser.parse(expression);
        //设置语言
        CronDescriptor descriptor = CronDescriptor.instance(Locale.CHINESE);
        return descriptor.describe(cron);
    }


    /**
     * 检查cron表达式的合法性
     *
     * @param cron cron exp
     * @return true if valid
     */
    public static boolean checkValid(String cron) {
        try {
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
            CronParser parser = new CronParser(cronDefinition);
            parser.parse(cron);
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("cron=%s not valid", cron));
            return false;
        }
        return true;
    }






}
