package importNew.condition;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:Cooper
 * @Date:2019/2/2 13:50
 */
@Slf4j
public class ConditionGeek {


    /**
     *http://www.importnew.com/9281.html
     *
     * AbstractQueuedSynchronizer缩写为AQS
     *
     * 而Condition自己也维护了一个队列，该队列的作用是维护一个等待signal信号的队列，两个队列的作用是不同，事实上，每个线程也仅仅会同时存在以上两个队列中的一个，流程是这样的：
     *
     * 线程1调用reentrantLock.lock时，线程被加入到AQS的等待队列中。
     * 线程1调用await方法被调用时，该线程从AQS中移除，对应操作是锁的释放。
     * 接着马上被加入到Condition的等待队列中，以为着该线程需要signal信号。
     * 线程2，因为线程1释放锁的关系，被唤醒，并判断可以获取锁，于是线程2获取锁，并被加入到AQS的等待队列中。
     * 线程2调用signal方法，这个时候Condition的等待队列中只有线程1一个节点，于是它被取出来，并被加入到AQS的等待队列中。 注意，这个时候，线程1 并没有被唤醒。
     * signal方法执行完毕，线程2调用reentrantLock.unLock()方法，释放锁。这个时候因为AQS中只有线程1，于是，AQS释放锁后按从头到尾的顺序唤醒线程时，线程1被唤醒，于是线程1回复执行。
     * 直到释放所整个过程执行完毕。
     * @param args
     */



    public static void main(String[] args) {

        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();
        Thread thread = new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info(String.format("我要等一个新信号--->%s", "LOCK"));
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("拿到一个信号！！" + "LOCK");
            reentrantLock.unlock();
        }, "waitThread1");

        thread.start();

        Thread thread1 = new Thread((Runnable) () -> {
            reentrantLock.lock();
            log.info("我拿到锁了");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("我发了一个信号！！");
            reentrantLock.unlock();
        }, "signalThread");

        thread1.start();


    }


}
