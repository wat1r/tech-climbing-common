package basic.concurrent.sema;

import com.google.common.base.Stopwatch;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class TestCar {
    //停车场同时容纳的车辆10
    private static Semaphore semaphore = new Semaphore(3);

    static Stopwatch stopwatch = Stopwatch.createStarted();

    public static void main(String[] args) {
        //模拟10辆车进入停车场
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(() -> {
                try {
                    System.out.println("====" + Thread.currentThread().getName() + "来到停车场");
                    if (semaphore.availablePermits() == 0) {
                        System.out.printf("%s :你好，车位不足，请耐心等待%n", Thread.currentThread().getName());
                    }
                    semaphore.acquire();//获取令牌尝试进入停车场
                    System.out.println(Thread.currentThread().getName() + " " + stopwatch.toString() + " 成功进入停车场");
                    Thread.sleep(new Random().nextInt(10000));//模拟车辆在停车场停留的时间
                    System.out.println(Thread.currentThread().getName() + " " + stopwatch.toString() + " 驶出停车场");
                    semaphore.release();//释放令牌，腾出停车场车位
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "号车");
            thread.start();
        }
    }
}
