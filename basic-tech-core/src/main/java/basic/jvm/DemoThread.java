package basic.jvm;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by FrankCooper
 * Date 2019/1/27 21:04
 * Description
 */
@Slf4j
public class DemoThread {

    static class Hello {
        static {
            log.info(String.format("%s ---> begin to init", Thread.currentThread().getName()));
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        int i = 0;
        while (i++ < 20) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    log.info(String.format("%s ---> begin to start", Thread.currentThread().getName()));
                    new Hello();
                    log.info(String.format("%s ---> begin to end", Thread.currentThread().getName()));
                }
            });
        }

    }
}
