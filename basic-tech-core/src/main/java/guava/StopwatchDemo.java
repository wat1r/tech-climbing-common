package guava;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/4/22 17:10
 * @description:
 */
public class StopwatchDemo {
    static StopwatchDemo handler = new StopwatchDemo();

    public static void main(String[] args) {

        handler.testOne();
    }


    private void testOne() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 10000; i++) {
            long nanos = stopwatch.elapsed(TimeUnit.NANOSECONDS);
            System.out.println(nanos);
        }

    }


}
