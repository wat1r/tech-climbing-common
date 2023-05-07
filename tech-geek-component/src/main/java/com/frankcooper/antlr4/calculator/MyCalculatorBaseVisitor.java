package com.frankcooper.antlr4.calculator;

import java.util.ArrayList;
import java.util.List;

//重写观察者方法
public class MyCalculatorBaseVisitor extends CalculatorBaseVisitor {

    @Override
    public Object visitDiv(CalculatorParser.DivContext ctx) {
        List<Double> lrValues = getLrValues(ctx.expr(0), ctx.expr(1));
        return lrValues.get(0) / lrValues.get(1);
    }

    @Override
    public Object visitMul(CalculatorParser.MulContext ctx) {
        List<Double> lrValues = getLrValues(ctx.expr(0), ctx.expr(1));
        return lrValues.get(0) * lrValues.get(1);
    }

    @Override
    public Object visitAdd(CalculatorParser.AddContext ctx) {
        List<Double> lrValues = getLrValues(ctx.expr(0), ctx.expr(1));
        return lrValues.get(0) + lrValues.get(1);
    }


    @Override
    public Object visitBracket(CalculatorParser.BracketContext ctx) {
        // 去掉括号, 取中间的一个孩子
        return this.visit(ctx.getChild(1));
    }

    @Override
    public Object visitMin(CalculatorParser.MinContext ctx) {
        List<Double> lrValues = getLrValues(ctx.expr(0), ctx.expr(1));
        return lrValues.get(0) - lrValues.get(1);
    }

    @Override
    public Object visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.getText());
    }

    public List<Double> getLrValues(CalculatorParser.ExprContext left, CalculatorParser.ExprContext right) {
        List<Double> lrValues = new ArrayList<>();
        double leftValue = getValue(left);
        double rightValue = getValue(right);

        lrValues.add(leftValue);
        lrValues.add(rightValue);
        return lrValues;
    }


    private double getValue(CalculatorParser.ExprContext context) {
        if (context.getChildCount() > 1) {
            return Double.parseDouble(this.visit(context).toString());
        } else {
            return Double.parseDouble(context.getText());
        }
    }

}
