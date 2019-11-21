package algorithm.stack;

import java.util.Stack;

/**
 * Created by FrankCooper
 * Date 2019/11/21 20:57
 * Description
 */
public class MinStack {

    private long min;
    private Stack<Long> stack;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if (stack.isEmpty()) {
            min = x;
            stack.push(0L);
        } else {
            long delta = x - min;
            stack.push(delta);
            min = delta < 0 ? x : min;
        }
    }

    public void pop() {
        long pop = stack.pop();
        min = pop < 0 ? (min - pop) : min;

    }

    public int top() {
        long peek = stack.peek();
        if (peek < 0) return (int) min;
        else return (int) (peek + min);
    }

    public int getMin() {
        return (int) min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(3);
        minStack.push(5);
        minStack.push(2);
        minStack.push(6);

        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.top());
        minStack.pop();


    }
}
