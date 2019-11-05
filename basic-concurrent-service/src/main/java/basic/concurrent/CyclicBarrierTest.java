package basic.concurrent;

import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by FrankCooper
 * Date 2019/11/5 8:13
 * Description
 */
public class CyclicBarrierTest {
    private static final CyclicBarrier barrier = new CyclicBarrier(5,
            new Runnable(){
                public void run(){	//每次线程到达屏障点，此方法都会执行
                    System.out.println("\n--------barrier action--------\n");
                }
            });
    public static void main(String[] args) {
        for(int i=0;i<5;i++){
            new Thread(new CyclicBarrierTest().new Worker()).start();
        }
    }
    class Worker implements Runnable{
        public void run(){
            try {
                System.out.println(Thread.currentThread().getName()+"--第一阶段");
                Thread.sleep(getRl());
                barrier.await();	//每一次await()都会阻塞，等5个线程都执行到这一步（相当于barrier++操作，加到初始化值5），才继续往下执行
                System.out.println(Thread.currentThread().getName()+"--第二阶段");
                Thread.sleep(getRl());
                barrier.await();	//每一次5个线程都到达共同的屏障节点，会执行barrier初始化参数中定义的Runnable.run()
                System.out.println(Thread.currentThread().getName()+"--第三阶段");
                Thread.sleep(getRl());
                barrier.await();
                System.out.println(Thread.currentThread().getName()+"--第四阶段");
                Thread.sleep(getRl());
                barrier.await();
                System.out.println(Thread.currentThread().getName()+"--第五阶段");
                Thread.sleep(getRl());
                barrier.await();
                System.out.println(Thread.currentThread().getName()+"--结束");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
    public static long getRl(){
        return Math.round(10000);
    }
}
