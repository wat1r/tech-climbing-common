package jsqlparser;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;

public class JsqlParserDemo {

    static  JsqlParserDemo handler = new JsqlParserDemo();

    public static void main(String[] args) throws JSQLParserException {
        handler.testOne();
    }


    private void testOne() throws JSQLParserException {
        String sql = "SELECT  *  from dw_dev.gplusosm_deposit_log WHERE  app_id = 11";
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();




        System.out.printf("SSS");

    }


}
