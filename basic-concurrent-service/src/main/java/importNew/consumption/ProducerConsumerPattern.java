package importNew.consumption;

/**
 * Created by FrankCooper
 * Date 2019/1/20 22:10
 * Description http://www.importnew.com/6695.html
 * 阻塞队列实现生产者消费者模式
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class ProducerConsumerPattern {

    public static void main(String args[]) {

        //Creating shared object
        BlockingQueue sharedQueue = new LinkedBlockingQueue();

        //Creating Producer and Consumer Thread
        Thread prodThread = new Thread(new importNew.consumption.Producer(sharedQueue));
        Thread consThread = new Thread(new importNew.consumption.Consumer(sharedQueue));

        //Starting producer and Consumer thread
        prodThread.start();
        consThread.start();


//        System.exit(0);
    }

}

//Producer Class in java
@Slf4j
class Producer implements Runnable {

    private final BlockingQueue sharedQueue;

    public Producer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                log.info("Produced: {}", i);
                sharedQueue.put(i);
            } catch (InterruptedException ex) {
                Logger.getLogger(importNew.consumption.Producer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


}

//Consumer Class in Java
@Slf4j
class Consumer implements Runnable {

    private final BlockingQueue sharedQueue;

    public Consumer(BlockingQueue sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                log.info("Consumed: " + sharedQueue.take());
            } catch (InterruptedException ex) {
                Logger.getLogger(importNew.consumption.Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

