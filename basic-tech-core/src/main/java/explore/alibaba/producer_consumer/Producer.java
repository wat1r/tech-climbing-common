package explore.alibaba.producer_consumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 11:44
 * @description:
 */
public class Producer implements Runnable {

    //多线程间是否启动变量，有强制从主内存中刷新的功能。即时返回线程的状态
    private volatile boolean isRunning = true;
    private BlockingQueue<Message> queue;

    public Producer(BlockingQueue<Message> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        while (isRunning) {
            this.queue.offer(processMessage());
        }
    }


    private Message processMessage() {
        String[] names = new String[]{"Alice", "Bob", "Cindy", "David", "Edison"};
        String[] subjects = new String[]{"A", "B", "C", "D", "E"};
        Random random = new Random();
        int nameIdx = random.nextInt(5);
        int subjectIdx = random.nextInt(5);
        int score = random.nextInt(100);
        return new Message(names[nameIdx], subjects[subjectIdx], score);

    }


    public void stop(){
        this.isRunning = false;
    }

}
