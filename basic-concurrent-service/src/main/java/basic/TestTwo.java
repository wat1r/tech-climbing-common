package basic;

import com.google.common.collect.Lists;

import java.util.concurrent.*;
import java.util.*;

public class TestTwo {

    /**
     * 批次执行10个线程分10次执行
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        //线程池10个线程
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //十个任务
        List<List<Task>> task = new ArrayList<>();
        int cnt = 10;
        while (cnt-- > 0) {
            List<Task> subTask = Lists.newArrayList(new Task(), new Task(), new Task(), new Task(), new Task(), new Task(), new Task(), new Task(), new Task(), new Task());
            task.add(subTask);
        }
        //记录任务执行时间
        long t1 = System.currentTimeMillis();
        CountDownLatch c;
        //循环任务组
        for (List<Task> startList : task) {
            //定义线程阻塞为10
            c = new CountDownLatch(10);
            for (Task agent : startList) {
                agent.setCountDownLatch(c);
                executorService.submit(agent);
            }
            //阻塞，等待十个任务都执行后，才继续下一批10任务
            c.await();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("执行时间：" + (t2 - t1) / 1000);
        executorService.shutdown();

    }

    static class Task implements Runnable {

        private CountDownLatch countDownLatch;

        @Override
        public void run() {
            try {
                System.out.println("开始启动节点：" + Thread.currentThread().getName());
                //模拟每个任务执行3秒钟
                Thread.sleep(3000);
                System.out.println(Thread.currentThread().getName() + "执行完毕");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }

        public void setCountDownLatch(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

    }

}