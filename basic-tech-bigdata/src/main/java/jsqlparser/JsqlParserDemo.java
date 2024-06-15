package jsqlparser;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.Parenthesis;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.IsNullExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.SelectUtils;

import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;


@Slf4j
public class JsqlParserDemo {

    static JsqlParserDemo handler = new JsqlParserDemo();

    public static void main(String[] args) throws JSQLParserException {
//        handler.testOne();
        handler.testTwo();


    }


    private void testOne() throws JSQLParserException {
        String sql = "SELECT  *  from dw_dev.gplusosm_deposit_log WHERE  app_id = 11";
        sql = "SELECT * INTO newtable FROM table1 WHERE 1=1";
        Select select = (Select) CCJSqlParserUtil.parse(sql);
        PlainSelect plainSelect = (PlainSelect) select.getSelectBody();
        System.out.printf("SSS");

//        SelectUtils.buildSelectFromTableAndSelectItems()
//        SelectUtils.buildSelectFromTableAndExpressions()


    }


    private void testTwo() throws JSQLParserException {
        String sql = "SELECT  *  from dw_dev.gplusosm_deposit_log WHERE  app_id = 11";
        sql = "SELECT * INTO newtable FROM table1 WHERE id = 1 and address = 'sh' and gender = 'male'";
//        Select select = (Select) CCJSqlParserUtil.parse(sql);
        getCloumnNames(sql);
        log.info("");
    }

    //装载where后面的字段名称并去重
    private Set<String> set = new HashSet<>();
    //解析出来的单个条件名称
    private String columnName = null;

    public String getCloumnNames(String sql) throws JSQLParserException {
        String columnNames = null;
        String allColumnNames = null;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(sql);
        Statement statement = CCJSqlParserUtil.parse(new StringReader(stringBuffer.toString()));
        if (statement instanceof Select) {
            Select istatement = (Select) statement;
            Expression where = ((PlainSelect) istatement.getSelectBody()).getWhere();
            ((AndExpression) where).setRightExpression(new AndExpression(new Column("1=1"), new Column("1=1")));


//            XorExpression where1 = new XorExpression()
//                    .withLeftExpression(
//                            new XorExpression()
//                                    .withLeftExpression(new Column("a"))
//                                    .withRightExpression(new Column("b")))
//                    .withRightExpression(new Column("c"));

            log.info(istatement.toString());
//            if (null != where) {
//                Set<String> sets = getParser(where);
//                StringBuffer st = new StringBuffer();
//                sets.stream().forEach(set -> {
//                    st.append(set + ",");
//                });
//                columnNames = st.toString();
//            }
        }

        if (null != columnNames && columnNames != "" && !columnNames.equals("")) {
            allColumnNames = columnNames.substring(0, columnNames.length() - 1);
        }
        return allColumnNames;
    }


    private Set<String> getParser(Expression expression) {
        //初始化接受获得的字段信息
        if (expression instanceof BinaryExpression) {
            //获得左边表达式
            Expression leftExpression = ((BinaryExpression) expression).getLeftExpression();
            //获得左边表达式为Column对象，则直接获得列名
            if (leftExpression instanceof Column) {
                columnName = ((Column) leftExpression).getColumnName();
                set.add(columnName);
            } else if (leftExpression instanceof InExpression) {
                this.parserInExpression(leftExpression);
            } else if (leftExpression instanceof IsNullExpression) {
                this.parserIsNullExpression(leftExpression);
            } else if (leftExpression instanceof BinaryExpression) {//递归调用
                getParser(leftExpression);
            } else if (expression instanceof Parenthesis) {//递归调用
                Expression expression1 = ((Parenthesis) expression).getExpression();
                getParser(expression1);
            }

//            获得右边表达式，并分解
            Expression rightExpression = ((BinaryExpression) expression).getRightExpression();
            if (rightExpression instanceof BinaryExpression) {
                this.parserBinaryExpression(rightExpression);
            } else if (rightExpression instanceof InExpression) {
                this.parserInExpression(rightExpression);
            } else if (rightExpression instanceof IsNullExpression) {
                this.parserIsNullExpression(rightExpression);
            } else if (rightExpression instanceof Parenthesis) {//递归调用
                Expression expression1 = ((Parenthesis) rightExpression).getExpression();
                getParser(expression1);
            }
        } else if (expression instanceof InExpression) {
            this.parserInExpression(expression);
        } else if (expression instanceof IsNullExpression) {
            this.parserIsNullExpression(expression);
        } else if (expression instanceof Parenthesis) {//递归调用
            Expression expression1 = ((Parenthesis) expression).getExpression();
            getParser(expression1);
        }
        return set;
    }

    /**
     * 解析in关键字左边的条件
     *
     * @param expression
     */
    public void parserInExpression(Expression expression) {
        Expression leftExpression = ((InExpression) expression).getLeftExpression();
        if (leftExpression instanceof Column) {
            columnName = ((Column) leftExpression).getColumnName();
            set.add(columnName);
        }
    }

    /**
     * 解析is null 和 is not null关键字左边的条件
     *
     * @param expression
     */
    public void parserIsNullExpression(Expression expression) {
        Expression leftExpression = ((IsNullExpression) expression).getLeftExpression();
        if (leftExpression instanceof Column) {
            columnName = ((Column) leftExpression).getColumnName();
            set.add(columnName);
        }
    }

    public void parserBinaryExpression(Expression expression) {
        Expression leftExpression = ((BinaryExpression) expression).getLeftExpression();
        if (leftExpression instanceof Column) {
            columnName = ((Column) leftExpression).getColumnName();
            set.add(columnName);
        }
    }

}
