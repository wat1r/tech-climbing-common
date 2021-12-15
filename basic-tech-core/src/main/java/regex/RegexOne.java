package regex;

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
        String src = "requestId=020229163913275810410000,\n" +
                "connId=10.129.20.175:56517:1519812048,\n" +
                "sequence=70585059,\n" +
                "encoding=1,serviceId=52,msgId=2,\n" +
                "xhead={firstAddr=49.234.16.109:26698,\n" +
                "        addrs=[49.234.16.109:26698, 10.129.20.175:56517],\n" +
                "        groupId=0, hostId=0, socId=MEIYU_991000801, spId=0, areaId=0,\n" +
                "        httpType=5866078, appId=991000801, spsId=, businessType=, lastAddr=10.129.20.175:56517,\n" +
                "        uniqueId=5CA115E9B68AD0418AB1DFF9A83D53B8\n" +
                "       },\n" +
                "body={endPointIp=223.104.1.201, areaId=572, accountType=25, appId=991000801, groupId=101,\n" +
                "        guid=8418903868447824755, endPointPort=30629, eventtime=2021-12-10 18:39:18,\n" +
                "        characterId=28431429369071320, userId=2630348279\n" +
                "        },\n" +
                "toAddr=null,receivedTime=1639132758104";

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

}
