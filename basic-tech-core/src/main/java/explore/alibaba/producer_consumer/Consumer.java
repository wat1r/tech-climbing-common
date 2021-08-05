package explore.alibaba.producer_consumer;


import com.alibaba.fastjson.JSON;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/8/4 12:09
 * @description:
 */
public class Consumer implements Runnable {
    private BlockingQueue<Message> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    //随机对象
    private static Random r = new Random();

    @Override
    public void run() {
        while (true) {
            try {
                //获取数据
                Message data = this.queue.take();
                //进行数据处理。休眠0 - 1000毫秒模拟耗时
                Thread.sleep(r.nextInt(1000));
                System.out.println("curr thread：" + Thread.currentThread().getName() + "，  " + JSON.toJSONString(data));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
