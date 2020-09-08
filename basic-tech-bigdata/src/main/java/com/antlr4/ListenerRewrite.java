package com.antlr4;

/**
 * @Date 2020/9/8
 * @Author Frank Cooper
 * @Description
 */
public class ListenerRewrite extends LearnAntlrBaseListener {
    @Override
    public void exitR(LearnAntlrParser.RContext ctx) {
        final String a = ctx.getChild(0).getText().toLowerCase();
        final String b = ctx.getChild(1).getText().toLowerCase();
        System.out.println(a + " " + b);
    }
}
