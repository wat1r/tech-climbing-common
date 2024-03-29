package basic.concurrent;

import com.google.common.base.Stopwatch;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
//        try {
//            Thread.sleep(1000);
//            System.out.println("test");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        for (int i = 0; i < 10; i++) {
//            schedule.postQueue("");
//        }
        executeEightAtNightPerDay();
    }


    /**
     * 每天晚上8点执行一次
     * 每天定时安排任务进行执行
     */
    public static void executeEightAtNightPerDay() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        long oneDay = 24 * 60 * 60 * 1000;
        long initDelay = getTimeMillis("15:08:00") - System.currentTimeMillis();
        initDelay = initDelay > 0 ? initDelay : oneDay + initDelay;

        executor.scheduleAtFixedRate(
                new EchoServer(),
                initDelay,
                oneDay,
                TimeUnit.MILLISECONDS);
    }


    /**
     * 获取指定时间对应的毫秒数
     *
     * @param time "HH:mm:ss"
     * @return
     */
    private static long getTimeMillis(String time) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
            DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
            Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
            return curDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }





}
