package basic.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by FrankCooper
 * Date 2019/11/4 22:38
 * Description
 */
public class SemaphoreExample2 {
    private static final int NUMBER = 5;    //限制资源访问数
    private static final Semaphore avialable = new Semaphore(NUMBER, true);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    avialable.acquire();
                    Thread.sleep(10 * 1000);
                    System.out.println(getNow() + "--" + Thread.currentThread().getName() + "--执行完毕");
                    avialable.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println(avialable.availablePermits());
        for (int i = 0; i < 10; i++) {
            pool.execute(runnable);
        }
        pool.shutdown();
    }


    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }

}
