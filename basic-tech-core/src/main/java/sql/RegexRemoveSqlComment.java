package sql;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/05/30/ 10:53
 * @description
 */
public class RegexRemoveSqlComment {

//    public static void main(String[] args) {
//        String sql = "            select    /* comment /* yada yada yada /* / // bla bla bla  \n" +
//                "                        1\n" +
//                "                                    */ t1.i\n" +
//                "                   ,''\"SRC''''\"''    as \"This''is''the\n" +
//                "                                ''source\"\n" +
//                "\n" +
//                "            from      t1 /* \"Comment 2\" - '' */ cross join t2 -- /* comment 3 */\n" +
//                "\n" +
//                "            where     t2.v = ''/*DST\"*\n" +
//                "                                /'' -- comment 4'";
//
//    }


    public static void main(String[] args) {
        String sql = "select /* comment /* yada yada yada /* / // bla bla bla\n" + "        1\n" + "        */ t1.i\n" + "   ,''\"SRC''''\"''    as \"This''is''the\n" + "        ''source\"\n" + "from t1 /* \"Comment 2\" - '' */ cross join t2 -- /* comment 3 */\n" + "where t2.v = ''/*DST\"*/\n" + "        /'' -- comment 4";
        sql = "  delete from sr_rpt.dwpt_login_index_m_zzhm where stat_date='${date}';\n" + "--delete from sr_rpt.dwpt_login_index_studio_mir2 where stat_date='${date}';\n" + "--跑历史数据小号表用'2024-04-30'日期\n" + "--征战鸿蒙 insert into sr_rpt.dwpt_login_index_m_zzhm(stat_date,stat_m,game_id,login_num)\n" + "select '${date}',concat(substr('${date}',1,7),'-01'),a.game_id,count(distinct a.pt_id) from \n" + "(select\n" + "      a.game_id,\n" + "      a.pt_id,\n" + "      count(distinct case when login_date >= date_add(last_login_date,-29) then login_date else null end ) as login_days\n" + "    from\n" + "      (\n" + "        select l.data_date, l.game_id, l.pt_id, l.login_date,\n" + "          max(l.data_date) over (partition by l.game_id, l.pt_id) as last_login_date\n" + "        from\n" + "          dw.pt_login_game l\n" + "        where\n" + "          part_date >= '${date-70}'\n" + "          and part_date <= '${date}'\n" + "          and cast(game_id as string) not like '791000%'\n" + "        union all\n" + "        select l.data_date, l.game_id, l.pt_id, l.login_date,\n" + "          max(l.data_date) over (partition by l.game_id, l.pt_id) as last_login_date\n" + "        from\n" + "          dw.pt_3rd_login_game_mid /* \\\"Comment 2\\\" - '' */  l\n" + "        where\n" + "          part_date >= '${date-70}'\n" + "          and part_date <= '${date}'\n" + "          and game_id = 89 and channel_id in ('352','388','389')\n" + "      ) a\n" + "      left outer join \n" + "      (select * from dw.pt_xh_new_mid where part_date='2024-04-30' and type = 1) b \n" + "      on a.pt_id = b.pt_id and a.game_id = b.game_id\n" + "    where\n" + "      a.last_login_date >= concat(substr('${date}',1,7),'-01')\n" + "      and a.last_login_date <= '${date}' and b.pt_id is null\n" + "    group by a.game_id, a.pt_id\n" + "    having";

        sql = "            select    /* comment /* yada yada yada /* / // bla bla bla  \n" + "                        1\n" + "                                    */ t1.i\n" + "                   ,''\"SRC''''\"''    as \"This''is''the\n" + "                                ''source\"\n" + "\n" + "            from      t1 /* \"Comment 2\" - '' */ cross join t2 -- /* comment 3 */\n" + "\n" + "            where     t2.v = ''/*DST\"*\n" + "                                /'' -- comment 4'";
        sql = "SELECT /*+ MAPJOIN(smallTable) */  /*+ BROADCAST(smallTable) */  smallTable.key, bigTable.value\n" + "\n" + "FROM smallTable JOIN bigTable ON smallTable.key = bigTable.key;";

        sql = "/*+ BROADCAST(smallTable) */   SELECT /*+ MAPJOIN(smallTable) */ smallTable.key, bigTable.value\n" + "FROM smallTable JOIN bigTable ON smallTable.key = bigTable.key;\n" + "-- This is a comment\n" + "-- /*+ BROADCAST(smallTable) */\n" + "-- /*+ BROADCAST(smallTable) */\n" + "SELECT /* comment */ 'This is a string' FROM dual;";
        sql = sql + " insert overwrite table dw.mir2_character_kill_monster  partition(part_date='${date}')\n" + "\n" + "SELECT area_id,group_id,character_name,sum(monster_kill_num) total_num from\n" + "(SELECT area_id,group_id,character_name,monster_kill_num from dw.mir2_character_kill_monster where part_date ='${date-1}'\n" + "union all\n" + "SELECT area_id,group_id,regexp_extract(act_param,'^怪物死亡\\:(.*)$',1) character_name ,count(DISTINCT log_time) monster_kill_num\n" + "from dw.mir2_act_log  where part_date = '${date}' and act_param like '怪物死亡%' \n" + "and regexp_extract(act_param,'^怪物死亡\\:(.*)$',1) is not null group by area_id,group_id,regexp_extract(act_param,'^怪物死亡\\:(.*)$',1)\n" + ")a\n" + "group by area_id,group_id,character_name";
        String cleanSql = removeComments(sql);
        System.out.println(cleanSql);
    }

//    public static String removeComments(String sql) {
//        // Define the regex pattern
//        String regex = "('.*?'|\".*?\")|/\\*.*?\\*/|--.*?(?=$|\\Z)";
//
//        // Compile the pattern
//        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE);
//        Matcher matcher = pattern.matcher(sql);
//
//        StringBuffer result = new StringBuffer();
//        while (matcher.find()) {
//            if (matcher.group(1) != null || (matcher.groupCount() >= 2 && matcher.group(2) != null)) {
//                matcher.appendReplacement(result, matcher.group()); // Keep quoted strings
//            } else {
//                matcher.appendReplacement(result, ""); // Remove comments
//            }
//        }
//        matcher.appendTail(result);
//
//        return result.toString();
//    }


    public static String removeComments(String sql) {
        String regex = "(--.*?$)|(/\\*(?!\\+.*?\\*/).*?\\*/)|('.*?'|\".*?\")";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL | Pattern.MULTILINE | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sql);

        StringBuffer result = new StringBuffer();
        while (matcher.find()) {
            if (matcher.groupCount() >= 3 && matcher.group(3) != null) {
//                matcher.appendReplacement(result, matcher.group(3));  // Preserve string literals
            } else if (matcher.groupCount() >= 2 && matcher.group(2) != null && matcher.group(2).contains("/*+")) {
                matcher.appendReplacement(result, matcher.group(2));  // Preserve hints
            } else {
                matcher.appendReplacement(result, "");  // Remove comments
            }
        }
//        regexp_replace(concat(round(b.nextday_rate * 100, 3), '.'), '\\.?0+$', '')
//        concat(round(b.nextday_rate * 100, 3), '%')
//
        matcher.appendTail(result);
        return result.toString();
    }
}
//hours_add(date_format(concat(stat_date, ' ', lpad(hour_name, 2, '0')), '%y-%m-%d %h:%m:%s'), 2)