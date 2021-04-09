package basic.concurrent;

import com.google.common.base.Stopwatch;

import java.util.concurrent.*;


/**
 * @author: Frank Cooper
 * @date: 2021/4/9 15:51
 * @description: https://www.pianshen.com/article/76491169599/
 * https://my.oschina.net/u/4271175/blog/3265034
 */
public class CommandSchedule {

    private ScheduledExecutorService mCommandService;
    private LinkedBlockingQueue<String> mCommandQueue;

    public CommandSchedule() {
        mCommandService = Executors.newScheduledThreadPool(1);
        mCommandQueue = new LinkedBlockingQueue<>();
        //1.当前任务执行时间小于间隔时间，每次到点即执行
        //2.当前任务执行时间大于等于间隔时间，任务执行后立即执行下一次任务。相当于连续执行了。
        mCommandService.scheduleAtFixedRate(new Runnable() {
            //每当上次任务执行完毕后，间隔一段时间执行。不管当前任务执行时间大于、等于还是小于间隔时间，执行效果都是一样的。
            //        mCommandService.scheduleWithFixedDelay(new Runnable() {
            long lastTime = System.currentTimeMillis();

            @Override
            public void run() {
                try {
                    String commandBean = mCommandQueue.take();
                    if (commandBean != null) {
                        System.out.println("time = " + (System.currentTimeMillis() - lastTime));
                        lastTime = System.currentTimeMillis();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }, 0, 30, TimeUnit.MILLISECONDS);
    }

    public void postQueue(String str) {
        mCommandQueue.offer(str);
    }


//    public static void main(String[] args) {
//        CommandSchedule schedule = new CommandSchedule();
//        for (int i = 0; i < 10; i++) {
//            schedule.postQueue("");
//        }
//    }

    public static void main(String[] args) {

//        Stopwatch stopwatch

        CommandSchedule schedule = new CommandSchedule();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            schedule.postQueue("");
        }
    }


}
