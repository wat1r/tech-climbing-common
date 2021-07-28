package basic.concurrent.lock_support.two;

import java.util.concurrent.locks.LockSupport;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/27 10:18
 * @description:
 */
public class LockSupportDemoOne {

    private static Thread mainThread;

    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        while (n-- > 0) {
//            waitTest();
            lockSupportTest();
        }

//        lockSupportOne();
//        ThreadA ta = new ThreadA("ta");
//        // 获取主线程
//        mainThread = Thread.currentThread();
//        System.out.println(Thread.currentThread().getName() + " start ta");
//        ta.start();
//        System.out.println(Thread.currentThread().getName() + " block");
//        // 主线程阻塞
//        LockSupport.park(mainThread);
//        System.out.println(Thread.currentThread().getName() + " continue");


    }

    public static void waitTest() throws InterruptedException {
        Object obj = new Object();
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            try {
                synchronized (obj) {
                    obj.wait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(sum);
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(1000);
        synchronized (obj) {
            obj.notify();
        }
    }

    private static void lockSupportTest() throws InterruptedException {
        Object obj = new Object();
        Thread A = new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += i;
            }
            LockSupport.park();
            System.out.println(sum);
        });
        A.start();
        //睡眠一秒钟，保证线程A已经计算完成，阻塞在wait方法
        Thread.sleep(1000);
        LockSupport.unpark(A);
    }


    private static void lockSupportOne() {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            System.out.println("lock support running");
        });
        t1.start();
        LockSupport.unpark(t1);
        System.out.println("lock support end");
    }


    static class ThreadA extends Thread {
        public ThreadA(String name) {
            super(name);
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " wake up others");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
        }
    }
}
