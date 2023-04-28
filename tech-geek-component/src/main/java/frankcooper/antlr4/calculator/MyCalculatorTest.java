package frankcooper.antlr4.calculator;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class MyCalculatorTest {

    public static void main(String[] args) {
        MyCalculatorTest handler = new MyCalculatorTest();
//        handler.testVisit();
        handler.testListener("1+2*3");
        handler.testListener("1-2+2*3");
        handler.testListener("1+4/2-2*3");
    }

    public void testVisit() {
        CharStream input = CharStreams.fromString("12 * (2 + 12) / 7");
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        CalculatorParser.ExprContext tree = parser.expr();
        MyCalculatorBaseVisitor myCalculatorBaseVisitor = new MyCalculatorBaseVisitor();
        System.out.println(myCalculatorBaseVisitor.visit(tree));
    }


    public void testListener(String input) {
        // 词法分析，获取token
        CharStream charStream = CharStreams.fromString(input);
        CalculatorLexer lexer = new CalculatorLexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 语法分析，获取parse tree
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree parseTree = parser.expr();
        ParseTreeWalker walker = new ParseTreeWalker();
        MyCalculatorBaseListener listener = new MyCalculatorBaseListener();
        walker.walk(listener, parseTree);
        int listenerResult = listener.getResult();
        System.out.println("Listener calculate result: " + listenerResult);
    }


}
