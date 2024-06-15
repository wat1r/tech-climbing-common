package regex;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexOne {

    static RegexOne handler = new RegexOne();

    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("^(?!(_|-|\\.))([a-zA-Z0-9_-]|(\\.){0,1}).*(?<!(\\.|-|_))");
////        pattern = Pattern.compile("^[A-Za-z0-9]{1}[A-Za-z0-9_-]*?\\.?[A-Za-z0-9_-]*?[A-Za-z0-9]{1}$");
////        pattern = Pattern.compile("^(?![^.]*?\\.[^.]*?\\.)[a-zA-Z][a-zA-Z\\d_\\.-]*?(?<!(\\.|-|_))");
//        pattern = Pattern.compile("^(?![\\._-])[a-zA-Z0-9_-]*\\.?[a-zA-Z0-9_-]*?(?<![\\._-])$");
//        String[] strs = {"&a123456888", "a123456888_", "a123456 *", "Ua123456.", "Ua1.23456", "asdf.as.dfa", "as.df.as.dfa", "Ua1_2-3456", "Ua1大23.456"};
//        for (String str : strs) {
//            handler.process(str, pattern);
//        }
        System.out.println("dddd");
        /**
         * dddd
         */
        System.out.println("dddd");
        String src = "requestId=020229163913275810410000,\n" + "connId=10.129.20.175:56517:1519812048,\n" + "sequence=70585059,\n" + "encoding=1,serviceId=52,msgId=2,\n" + "xhead={firstAddr=49.234.16.109:26698,\n" + "        addrs=[49.234.16.109:26698, 10.129.20.175:56517],\n" + "        groupId=0, hostId=0, socId=MEIYU_991000801, spId=0, areaId=0,\n" + "        httpType=5866078, appId=991000801, spsId=, businessType=, lastAddr=10.129.20.175:56517,\n" + "        uniqueId=5CA115E9B68AD0418AB1DFF9A83D53B8\n" + "       },\n" + "body={endPointIp=223.104.1.201, areaId=572, accountType=25, appId=991000801, groupId=101,\n" + "        guid=8418903868447824755, endPointPort=30629, eventtime=2021-12-10 18:39:18,\n" + "        characterId=28431429369071320, userId=2630348279\n" + "        },\n" + "toAddr=null,receivedTime=1639132758104";

        handler.reshape(src);


    }

    public void removeAnnotation(String src) {
        String dest = null;


        /**
         * ddd
         */
    }


    public boolean process(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.matches()) {
            System.out.printf("%s--->%s\n", str, "true");
            return true;
        }
        System.out.printf("%s--->%s\n", str, "false");
        return false;
    }


    public boolean hasSensitiveSymbol(String name) {
//        Pattern pattern = Pattern.compile("[\\s\\\\/:\\*\\?\\\"<>\\|]");
        Pattern pattern = Pattern.compile(".*(\\s|\\?|\\|:|\\*|\\.|;|,|<|>|/|\").*");
        Matcher matcher = pattern.matcher(name);
        if (matcher.matches()) {
            System.out.println("dd");
        }
        return false;
    }


    public String reshape(String src) {
        StringBuilder res = new StringBuilder();
        Pattern pattern = Pattern.compile("(?:([a-zA-Z]+)([={\\[]+))?([a-zA-Z]+)(=)([^=,\\n]+)?[,\\n](\\s*\\},)?");
        Matcher matcher = pattern.matcher(src);

        while (matcher.find()) {
            for (int i = 1; i < matcher.groupCount(); i++) {
                String cur = matcher.group(i);
                if (cur != null && cur.equals("=")) {
                    res.append(":");
                } else if (cur != null && (cur.contains("{"))) {
                    res.append(cur).append("=\"{");
                } else if (cur != null && (cur.contains("["))) {
                    res.append(cur).append("=\"[");
                } else if (cur != null) {
                    res.append("\"").append(cur).append("\"");
                }
            }
            res.append(",");
        }
        res.deleteCharAt(res.length() - 1);
        System.out.println(res.toString());
        return res.toString();

    }


    static class _1st {
        public static void main(String[] args) {
            _1st handler = new _1st();
            String line = "hadoop job  -kill  job_1655360675765_172628";
            handler.getJobId(line);
        }

        public String getJobId(String line) {
            String p = "(?<=-kill)(.*)";
            Matcher matcher = Pattern.compile(p).matcher(line);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        }
    }


    static class _2nd {

        public static void main(String[] args) {
            String sql = "select c.*  \n" + "dw.v_test_pt_consumes  dw.v_test_pt_consume)  dw.test_pt_consume dw.v_test_pt_consume from\n" + "(select part_date,pt_id from dw.v_test_pt_login_game where part_date<='${date}' and part_date >= '${start_date}'  and area_id = ${area_id} group by part_date,pt_id) a\n" + "left outer join\n" + "(select part_date,pt_id,sum(consume_amount) consume_amount from dw.v_test_pt_consume where part_date<='${date}' and part_date >= '${start_date}' and area_id = ${area_id} group by part_date,pt_id ) b\n" + "on a.part_date = b.part_date and a.pt_id = b.pt_id\n" + "left outer join\n" + "(select * from dw.v_test_pt_login_game_bitmap_mid where part_date='${date}'  and area_id = ${area_id} ) c\n" + "on a.pt_id = c.pt_id\n" + "left outer join\n" + "(select pt_id,first_consume_date,consume_amount from dw.v_test_pt_consume_bitmap_mid where  part_date='${date}' and first_consume_date >= '${start_date}'  and area_id = ${area_id}  ) d\n" + "on a.pt_id = d.pt_id;\n";
//            Map<String, String> map = new LinkedHashMap<String, String>() {{
            Map<String, String> map = new LinkedHashMap<String, String>() {{
                put("dw.v_test_pt_consume", "*****************");
                put("dw.v_test_pt_consume_bitmap_mid", "##################");
            }};
            System.out.println(sql);
            System.out.println("------------------------------------------------------");
            for (String key : map.keySet()) {
                StringBuffer sb = new StringBuffer();
                regex(sql, key, map.get(key), sb);
                sql = sb.toString();
            }
            System.out.println(sql);

        }


        public static void regex(String sql, String key, String value, StringBuffer sb) {
            Pattern DATE_TYPE_FIELD_PATTERN = Pattern.compile("(" + key + ")" + "(\\s|\\r\\n|\\n|\\))", Pattern.DOTALL);
            Matcher matcher = DATE_TYPE_FIELD_PATTERN.matcher(sql);
            while (matcher.find()) {
                matcher.appendReplacement(sb, value + matcher.group(2) + " ");
            }
            matcher.appendTail(sb);
        }


    }


    static class _3rd {
        public static void main(String[] args) {
            List<String> list = new ArrayList<String>() {{
                add("2023/10/13 (星期三),793871,793878,2342255,63,763,2.91,0.0803,36.73,60.5");
                add("2023-10-14 (四),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("2023-10-14 (星期三),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("2023-10-4 (星期三),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("2023-10-4 星期三),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("2023-10-4 星期三  ,793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("23-10-14 (四),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("23-10/14 (四),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("023-10-14 (四),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("23-1-14 (四),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("23-1-1 (四),793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
                add("2023/10/13 (星期三) 哈哈哈,793872,793878,2342255,63763,2.91,0.0803,36.73,60.5");
            }};
            list.forEach(e -> {
                String[] arr = e.split(",", -1);
                System.out.printf("%s->%s\n", arr[0], regex(arr[0]));
            });
        }

//

        public static String regex(String input) {
            Matcher matcher = Pattern.compile("((\\d{4}[-]\\d{1,2}[-]\\d{1,2})|(\\d{4}[\\/]\\d{1,2}[\\/]\\d{1,2}))\\s?(.*)?\\((星期)?(一|二|三|四|五|六|天)+\\)", Pattern.DOTALL).matcher(input);
            if (matcher.matches()) {
                return !StringUtils.isEmpty(matcher.group(1)) ? matcher.group(1) : (matcher.groupCount() >= 2 ? matcher.group(2) : input);
            }
            return input;
        }
    }

}
