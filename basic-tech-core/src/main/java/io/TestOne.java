package io;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestOne {
    public static void main(String[] args) throws IOException {

//        testOne();

        testTwo();
    }

    static String regex = "'\\$\\{(.*)\\}'";
    static Map<String, Integer> map = Maps.newHashMap();

    private static void testTwo() throws IOException {
        List<String> lines = FileUtils.readLines(new File("D:\\Dev\\Documents\\GFile\\dev\\metasys_prod_action_relation\\action.txt"), "UTF-8");
        int i = 0;
        for (String line : lines) {
            if (line.contains("command")) {
//                System.out.printf("%s\n", line);
            }
            String[] strs = line.split(" |,|=|\\(|<|>|\\)|;");
            for (String str : strs) {
                Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
                Matcher matcher = pattern.matcher(str);
                if (matcher.find()) {
//                    System.out.println("替换结果: " + str);
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }

            }
            i++;
        }
        System.out.printf("%s\n", JSON.toJSONString(map));
        System.out.printf("%d\n", i);
    }


    private static void testOne() {
        try {

            String yyyyMMdd = String.format("/data/patsnap_chemical_bak_%s", new SimpleDateFormat("yyyyMMdd").format(new Date()));


            BufferedWriter backupWriter = new BufferedWriter(new FileWriter("D:\\Test\\20190212", true));
//            BufferedWriter backupWriter = new BufferedWriter(new FileWriter("D:\\Test\\data\\backup\\20190212", true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
