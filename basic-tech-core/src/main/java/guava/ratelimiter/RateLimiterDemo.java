package guava.ratelimiter;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/11/17 15:35
 * @description:
 */

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Slf4j
public class RateLimiterDemo {

    static void submitTasks1() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        RateLimiter rateLimiter = RateLimiter.create(5); // rate is "5 permits per second"
        IntStream.range(0, 10).forEach(i -> pool.submit(() -> {
            if (rateLimiter.tryAcquire()) {
                try {
                    log.info("start");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            } else {
                log.warn("限流");
            }
        }));
        pool.shutdown();
        /*
16:18:18.784 [pool-1-thread-1] INFO  RateLimiterDemo - start
16:18:18.784 [pool-1-thread-7] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-2] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-4] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-5] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-6] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-9] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-3] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-10] WARN  RateLimiterDemo - 限流
16:18:18.784 [pool-1-thread-8] WARN  RateLimiterDemo - 限流
         */
    }

    static void submitTasks2() {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        RateLimiter rateLimiter = RateLimiter.create(5); // rate is "5 permits per second"
        IntStream.range(0, 10).forEach(i -> pool.submit(() -> {
            rateLimiter.acquire();
            log.info("start");
            try {
                Thread.sleep(1 * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        pool.shutdown();
        /*
16:18:56.030 [pool-1-thread-1] INFO  RateLimiterDemo - start
16:18:56.227 [pool-1-thread-10] INFO  RateLimiterDemo - start
16:18:56.428 [pool-1-thread-9] INFO  RateLimiterDemo - start
16:18:56.627 [pool-1-thread-8] INFO  RateLimiterDemo - start
16:18:56.827 [pool-1-thread-7] INFO  RateLimiterDemo - start
16:18:57.028 [pool-1-thread-6] INFO  RateLimiterDemo - start
16:18:57.226 [pool-1-thread-5] INFO  RateLimiterDemo - start
16:18:57.426 [pool-1-thread-4] INFO  RateLimiterDemo - start
16:18:57.629 [pool-1-thread-3] INFO  RateLimiterDemo - start
16:18:57.826 [pool-1-thread-2] INFO  RateLimiterDemo - start
         */
    }

    static void submitTasks3() {
        RateLimiter r = RateLimiter.create(5);
        log.info("start");
        for (; ; ) {
            log.info("get 1 tokens: " + r.acquire() + "s");
        }
        /*
16:15:46.310 [main] INFO RateLimiterDemo - start
16:15:46.315 [main] INFO RateLimiterDemo - get 1 tokens: 0.0s
16:15:46.513 [main] INFO RateLimiterDemo - get 1 tokens: 0.193752s
16:15:46.709 [main] INFO RateLimiterDemo - get 1 tokens: 0.194875s
16:15:46.911 [main] INFO RateLimiterDemo - get 1 tokens: 0.199033s
16:15:47.113 [main] INFO RateLimiterDemo - get 1 tokens: 0.197833s
16:15:47.312 [main] INFO RateLimiterDemo - get 1 tokens: 0.195898s
         */
    }

    static void submitTasks4() {
        RateLimiter r = RateLimiter.create(5);
        log.info("start");
        for (; ; ) {
            if (r.tryAcquire()) {
                log.info("run");
            }
        }
        /*
16:17:17.098 [main] INFO  RateLimiterDemo - start
16:17:17.100 [main] INFO  RateLimiterDemo - run
16:17:17.296 [main] INFO  RateLimiterDemo - run
16:17:17.496 [main] INFO  RateLimiterDemo - run
16:17:17.696 [main] INFO  RateLimiterDemo - run
         */
    }


    public static void test1() throws InterruptedException {
        //RateLimiter limiter = RateLimiter.create(10,2, TimeUnit.SECONDS);//QPS 100
        RateLimiter limiter = RateLimiter.create(10);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 30; i++) {
            double time = limiter.acquire();
            long after = System.currentTimeMillis() - start;
            if (time > 0D) {
                System.out.println(i + ",limited,等待:" + time + "，已开始" + after + "毫秒");
            } else {
                System.out.println(i + ",enough" + "，已开始" + after + "毫秒");
            }
            //模拟冷却时间，下一次loop可以认为是bursty开始
            if (i == 9) {
                Thread.sleep(2000);
            }
        }
        System.out.println("total time：" + (System.currentTimeMillis() - start));
    }


    public static void main(String[] args) throws InterruptedException {
//        submitTasks1();
//        submitTasks2();
        //submitTasks3();
        //submitTasks4();
        test1();
    }


    static class _1st {
        public static ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<String, RateLimiter>();

        //初始化限流工具RateLimiter
        static {
            createResourceRateLimiter("order", 50);
        }

        public static void createResourceRateLimiter(String resource, double qps) {
            if (resourceRateLimiter.contains(resource)) {
                resourceRateLimiter.get(resource).setRate(qps);
            } else {
//创建限流工具，每秒发出50个令牌指令
                RateLimiter rateLimiter = RateLimiter.create(qps);
                resourceRateLimiter.putIfAbsent(resource, rateLimiter);
            }
        }

        public static void main(String[] args) {
            for (int i = 0; i < 5000; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//如果获得令牌指令，则执行业务逻辑
                        if (resourceRateLimiter.get("order").tryAcquire(10, TimeUnit.MICROSECONDS)) {
                            System.out.println("执行业务逻辑");
                        } else {
                            System.out.println("waiting");
                        }
                    }
                }).start();

            }
        }
    }


    public static class RateLimiterTest {

        private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private static final int THREAD_COUNT = 25;

        public static void main(String[] args) {
            testRateLimiter1();
        }

        public static void testRateLimiter1() {
            RateLimiter rateLimiter = RateLimiter.create(5);
            Thread[] ts = new Thread[THREAD_COUNT];
            for (int i = 0; i < THREAD_COUNT; i++) {
                ts[i] = new Thread(new RateLimiterThread(rateLimiter), "RateLimiterThread-" + i);
            }

            for (int i = 0; i < THREAD_COUNT; i++) {
                ts[i].start();
            }

            for (; ; ) ;
        }

        public static class RateLimiterThread implements Runnable {

            private RateLimiter rateLimiter;

            public RateLimiterThread(RateLimiter rateLimiter) {
                this.rateLimiter = rateLimiter;
            }

            @Override
            public void run() {
                rateLimiter.acquire(1);

                System.out.println(Thread.currentThread().getName() + "获取到了令牌，时间 = " + FORMATTER.format(new Date()));
            }

        }

    }
}


