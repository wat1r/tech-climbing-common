package apache.commons.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/02/29/ 14:15
 * @description
 */
public class StringUtilsTest {

    public static final String FIELD_TERMINATED_DEFAULT = "\\t";
    public static final String NULL_DEFINED_DEFAULT = "";
    private static final String DEFAULT_FIELD_SPLIT = "\t";
    private static final String NULL_VALUE = "null";
    private static final String LIMIT = "LIMIT";

    public static void main(String[] args) {
//        strip();
//        split();

        limitTest();

    }

    private static void strip() {
        String oldStr = "{[Hello,word!!!]}";
//返回结果：[Hello,word!!!]
        String newStr = StringUtils.strip(oldStr, "{}");
        System.out.println(newStr);
        oldStr = " {[Hello,word!!!]} ";
        newStr = StringUtils.strip(StringUtils.strip(oldStr), "{}");
        System.out.println(newStr);
    }


    private static void split() {


        String input = "2023-12-31\tCN\tfree\tios-all\t989673964\t王者荣耀\tTencent\tTencent\t1000600000523259\t5993255\t357474134\t67253208\t-794184093\t21.807873\t动作游戏,MOBA\t1\t2015-10-26\t\t\t";
        String[] fields = input.split("\t", -1);
        StringBuilder row = new StringBuilder();
        for (int j = 1; j <= fields.length; j++) {
            if (StringUtilsTest.NULL_DEFINED_DEFAULT.equals(fields[j - 1])) {
                //当字段的值为空，则设置为null
                row.append(NULL_VALUE).append(DEFAULT_FIELD_SPLIT);
            } else {
                row.append(fields[j - 1]).append(DEFAULT_FIELD_SPLIT);
            }
        }
        System.out.println("");
    }


    private static void limitTest() {
        String sql = "SELECT 17 - 4 / 50 - 16 AS `_c0`, 17 - 4 / 34 AS `_c12`, 13 / 34 AS `_c13`, 13 / 50 - 16 AS `_c20` FROM `dw`.`swydny_user_card` WHERE `part_date` = '2024-01-03 18:20:00' LIMIT 20000";
        String originSql = "SELECT 17 - 4 / 50 - 16, ((17 - 4) / 34) _c12, 13 / 34 _c13, 13 / (50 - 16) _c20 FROM dw.swydny_user_card WHERE part_date = '2024-01-03 18:20:00' limit 10";
        limit(sql,originSql);
    }


    private static void limit(String sql, String target) {
        //只要limit操作
        Map<String, Integer> srSqlMap = repeatTimes(sql, Arrays.asList(" LIMIT ", " limit "));
        if (srSqlMap.size() == 1) {
            String originSql = target;
            Map<String, Integer> originSqlMap = repeatTimes(originSql, Arrays.asList(" LIMIT ", " limit "));
            int originSqlLimitTimes = originSqlMap.size();
            int limitStart = sql.indexOf(LIMIT);
            String limitStr = sql.substring(limitStart);
            if (originSqlLimitTimes == 0) {
                String resultSql = originSql + " " + limitStr;
            } else if (originSqlLimitTimes == 1) {
                int tmpLimitStart = originSql.indexOf(originSqlMap.keySet().stream().map(String::toString).collect(Collectors.joining("")));
                limitStart = tmpLimitStart < 0 ? limitStart : tmpLimitStart;
                String resultSql = originSql.substring(0, limitStart);
                resultSql += limitStr;
            }
        }
    }

    /**
     * 字符串st中，出现keyword的次数统计
     *
     * @param st
     * @param keywords
     * @return
     */
    public static Map<String, Integer> repeatTimes(String st, List<String> keywords) {
        Map<String, Integer> resMap = new HashMap<>();
        int count = 0;
        for (String keyword : keywords) {
            while (st.indexOf(keyword) >= 0) {
                st = st.substring(st.indexOf(keyword) + keyword.length());
                count++;
                resMap.put(keyword.trim(), count);
            }
        }
        return resMap;
    }
}
