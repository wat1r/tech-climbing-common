package lock.one;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTestOne {
    public static void main(String args[]) {
        LockObject lo = new LockObject();
        MyThreadOne t1 = new MyThreadOne(lo);
        MyThreadOne t2 = new MyThreadOne(lo);
        Thread ta = new Thread(t1, "A");
        Thread tb = new Thread(t2, "B");

        ta.start();
        tb.start();

    }
}

class LockObject {
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void LockFuc() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "得到了锁");
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "正在进行读操作" + i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName() + "释放了锁");
        }
    }
}

class MyThreadOne implements Runnable {
    LockObject lo = null;

    public MyThreadOne(LockObject lo) {
        this.lo = lo;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        lo.LockFuc();


    }

}