package com.frankcooper.spark;

import com.alibaba.fastjson.JSONObject;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.apache.spark.sql.catalyst.parser.SqlBaseBaseListener;
import org.apache.spark.sql.catalyst.parser.SqlBaseLexer;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;

import java.util.Arrays;
import java.util.function.Function;

public class MySqlBaseTest {
    public static void main(String[] args) {
        String sql = "WITH gun_list AS ( SELECT mid, count(DISTINCT character_id) AS character_nums, CASE WHEN count(DISTINCT character_id) > 1 THEN count(DISTINCT character_id) - 1 ELSE 0 END AS gun_times FROM dw.gmqstsea_character_login_glog WHERE part_date BETWEEN '2023-03-30' AND '2023-04-02' AND area_id IN (10001) GROUP BY mid ) SELECT * FROM gun_list";
        process("statement", sql, SqlBaseParser::singleStatement);

    }


    public static void process(String symbol, String sql, Function<SqlBaseParser, ParserRuleContext> parseFunction) {
        SqlBaseLexer lexer = new SqlBaseLexer(new CaseInsensitiveStream(CharStreams.fromString(sql)));
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        SqlBaseParser parser = new SqlBaseParser(tokenStream);
        parser.addParseListener(new SqlBaseBaseListener());
        parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
        ParserRuleContext context = parseFunction.apply(parser);
        MySqlBaseBaseVisitor visitor = new MySqlBaseBaseVisitor();
        visitor.visit(context);
//        System.out.println(JSONObject.toJSONString(context));

    }
}
