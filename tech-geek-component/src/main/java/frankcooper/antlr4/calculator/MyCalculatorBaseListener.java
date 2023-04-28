package frankcooper.antlr4.calculator;

import java.util.Stack;

public class MyCalculatorBaseListener extends CalculatorBaseListener {

    private Stack<Integer> result = new Stack<>();

    public int getResult() {
        return result.pop();
    }

    @Override
    public void exitAdd(CalculatorParser.AddContext ctx) {
        //右边的值会先出栈
        int right = this.result.pop();
        int left = this.result.pop();
        //计算后的值放入栈中
        result.push(left + right);
    }


    @Override
    public void exitMul(CalculatorParser.MulContext ctx) {
        int right = result.pop();
        int left = result.pop();
        result.push(left * right);
    }


    @Override
    public void exitMin(CalculatorParser.MinContext ctx) {
        int right = result.pop();
        int left = result.pop();
        result.push(left - right);
    }

    @Override
    public void exitDiv(CalculatorParser.DivContext ctx) {
        int right = result.pop();
        int left = result.pop();
        result.push(left /right);
    }

    @Override
    public void exitInt(CalculatorParser.IntContext ctx) {
        result.push(Integer.valueOf(ctx.getText()));
    }
}
