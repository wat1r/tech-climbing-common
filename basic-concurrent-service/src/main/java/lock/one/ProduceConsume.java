package lock.one;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 生产者与消费者问题
 */
public class ProduceConsume {
    public static void main(String[] args) {
        SyncStack ss = new SyncStack();
        Produce pd = new Produce(ss);
        Consume cs = new Consume(ss);
        Thread t1 = new Thread(pd);
        Thread t2 = new Thread(cs);
        t1.start();
        t2.start();
    }
}

/*
 * 馒头实体类
 */
class ManTou {
    private int id;

    public ManTou(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "ManTou " + getId();
    }
}

/*
 * 馒头框类
 */
class SyncStack {
    Lock lock = new ReentrantLock();

    Condition full = lock.newCondition();

    Condition empty = lock.newCondition();
    int index = 0;
    ManTou[] mtArray = new ManTou[6];

    public void push(ManTou mt) {
        lock.lock();
        try {
            while (index == mtArray.length) {
                try {
                    full.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            empty.signal();
            mtArray[index] = mt;
            index++;
            System.out.println("生产了" + mt);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void pop() {
        lock.lock();
        try {
            while (index == 0) {
                try {
                    empty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            full.signal();
            index--;
            System.out.println("消费了" + mtArray[index]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/*
 * 生产者
 */
class Produce implements Runnable {
    SyncStack ss = null;

    public Produce(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 20; i++) {
            ManTou mt = new ManTou(i);
            if (ss != null) {
                ss.push(mt);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

/*
 * 消费者
 */
class Consume implements Runnable {
    SyncStack ss = null;

    public Consume(SyncStack ss) {
        this.ss = ss;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 20; i++) {
            if (ss != null) {
                ss.pop();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}