package interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankCooper
 * Date 2019/3/27 22:24
 * Description
 */
public class DeadLock {


    public static void main(String[] args) {
        final List<Integer> list1 = new ArrayList<>();
        final List<Integer> list2 = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list1) {
                    for (Integer i : list1) {
                        System.out.println(i);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (list2) {
                        for (Integer i : list2) {
                            System.out.println(i);
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list2) {
                    for (Integer i : list2) {
                        System.out.println(i);
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (list1) {
                        for (Integer i : list1) {
                            System.out.println(i);
                        }
                    }
                }
            }
        }).start();


    }


}
