package basic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestCount {

    public static void main(String[] args) throws InterruptedException {
        TestCount handler = new TestCount();
//        handler.test();
        handler.testOne();
    }

    private void testOne() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println("First batch has executed!");
                latch.countDown();
            });
        }

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    latch.await();
                    System.out.println("Second batch has executed!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        while (latch.getCount() != 1) {
            Thread.sleep(1000);
        }
        latch.countDown();
        System.out.println("Wait for first batch finish!");

    }

    private void test() {
        int count = 3;

        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int j = 1; j < 100; j++) {
            for (int i = 0; i < count; i++) {
                new WorkThread(i, countDownLatch, j).start();
            }
        }

        try {
            System.out.println("等待" + count + "个线程运行完毕...");
            countDownLatch.await();

            System.out.println(count + "个线程已运行完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class WorkThread extends Thread {
        private int seq;
        private CountDownLatch countDownLatch;
        private int j;

        public WorkThread(int seq, CountDownLatch countDownLatch, int j) {
            this.seq = seq;
            this.countDownLatch = countDownLatch;
            this.j = j;
        }

        @Override
        public void run() {
            try {
                System.out.println("线程" + this.seq + "开始运行..." + j);
                Thread.sleep(2000);
                System.out.println("线程" + this.seq + "运行结束");

                countDownLatch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
