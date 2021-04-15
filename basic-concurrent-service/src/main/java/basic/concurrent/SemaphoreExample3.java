package basic.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author: Frank Cooper
 * @date: 2021/4/15 19:33
 * @description:
 */

/**
 * 1、acquire(int permits)
 * 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞，或者线程已被中断。
 * 就好比是一个学生占两个窗口。这同时也对应了相应的release方法。
 * 2、release(int permits)
 * 释放给定数目的许可，将其返回到信号量。这个是对应于上面的方法，一个学生占几个窗口完事之后还要释放多少
 * 3、availablePermits()
 * 返回此信号量中当前可用的许可数。也就是返回当前还有多少个窗口可用。
 * 4、reducePermits(int reduction)
 * 根据指定的缩减量减小可用许可的数目。
 * 5、hasQueuedThreads()
 * 查询是否有线程正在等待获取资源。
 * 6、getQueueLength()
 * 返回正在等待获取的线程的估计数目。该值仅是估计的数字。
 * 7、tryAcquire(int permits, long timeout, TimeUnit unit)
 * 如果在给定的等待时间内此信号量有可用的所有许可，并且当前线程未被中断，则从此信号量获取给定数目的许可。
 * 8、acquireUninterruptibly(int permits)
 * 从此信号量获取给定数目的许可，在提供这些许可前一直将线程阻塞。
 */
public class SemaphoreExample3 {

    static Semaphore semaphore = new Semaphore(3);//每次只能由3个线程在打饭

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {//10个学生打饭
            new Student(semaphore, "学生" + i).start();
        }
    }


    static class Student extends Thread {
        private Semaphore semaphore;
        private String name;

        public Student(Semaphore sema, String na) {
            this.semaphore = sema;
            this.name = na;
        }


        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(name + " --> 拿到了打饭的许可");
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(name + " --> 打好饭了，释放该窗口");
                semaphore.release();
            }
        }
    }
}
