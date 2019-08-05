package importNew.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by FrankCooper
 * Date 2019/8/5 22:13
 * Description
 */
public class ThreadLocalTest {
    ThreadLocal<Long> longLocal = new ThreadLocal<Long>();

    ThreadLocal<String> stringLocal = new ThreadLocal<String>();


    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());

    }


    public long getLong() {

        return longLocal.get();

    }


    public String getString() {

        return stringLocal.get();

    }


    public static void main(String[] args) throws InterruptedException {

        final ThreadLocalTest test = new ThreadLocalTest();


        test.set();

        System.out.println("A--->" + test.getLong());
        System.out.println("A--->" + test.getString());


        Thread thread1 = new Thread(() -> {
            test.set();
            System.out.println(test.getLong());
            System.out.println(test.getString());

        });

        thread1.start();
        thread1.join();


        System.out.println("B--->" + test.getLong());

        System.out.println("B--->" + test.getString());

    }
}

class DemoTask implements Runnable {


    // Atomic integer containing the next thread ID to be assigned

    private static final AtomicInteger nextId = new AtomicInteger(0);


    // Thread local variable containing each thread's ID

    private static final ThreadLocal<Integer> threadId =

            new ThreadLocal<Integer>() {

                @Override

                protected Integer initialValue() {

                    return nextId.getAndIncrement();

                }

            };


    // Returns the current thread's unique ID, assigning it if necessary

    public int getThreadId() {

        return threadId.get();

    }


    // Returns the current thread's starting timestamp

    private static final ThreadLocal<Date> startDate =

            new ThreadLocal<Date>() {

                protected Date initialValue() {

                    return new Date();

                }

            };


    @Override
    public void run() {
        System.out.printf("Starting Thread: %s : %sn", getThreadId(), startDate.get());

        try {

            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.printf("Thread Finished: %s : %sn",

                getThreadId(), startDate.get());

    }

}
