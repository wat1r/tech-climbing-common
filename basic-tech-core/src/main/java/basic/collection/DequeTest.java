package basic.collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class DequeTest {
    public static void main(String[] args) {
//        queueTest();
//        dequeTest();
        dequeTest1();
    }

    private static void queueTest() {
        Queue<String> queue = new LinkedList<String>();
        //追加元素
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        queue.offer("four");
        System.out.println(queue);
        //从队首取出元素并删除
        String poll = queue.poll();
        System.out.println(poll);
        System.out.println(queue);
        //从队首取出元素但是不删除
        String peek = queue.peek();
        System.out.println(peek);
        System.out.println(queue);
        //遍历队列，这里要注意，每次取完元素后都会删除，整个
        //队列会变短，所以只需要判断队列的大小即可
        while (queue.size() > 0) {
            System.out.println(queue.poll());
        }
    }

    private static void dequeTest() {
        Deque<String> deque = new LinkedList<String>();
        deque.push("a");
        deque.push("b");
        deque.push("c");
        System.out.println(deque);
        //获取栈首元素后，元素不会出栈
        String str = deque.peek();
        System.out.println(str);
        System.out.println(deque);
        while (deque.size() > 0) {
            //获取栈首元素后，元素将会出栈
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }

    private static void dequeTest1() {
        Deque<Integer> deque = new LinkedList<>();
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        System.out.println(deque);
        Integer in = deque.peek();
        System.out.println(in);
        System.out.println(deque);
        while (deque.size() > 0) {
            System.out.println(deque.pop());
        }
        System.out.println(deque);
    }
}
