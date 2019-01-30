package basic.jvm;

import com.alibaba.fastjson.JSON;

import java.util.Map;
import java.util.Scanner;

/**
 * Created by FrankCooper
 * Date 2019/1/30 21:07
 * Description
 */
public class JVMTools {



    public static void main(String[] args) {

//        jps();
//        jstack();
        jconsoleOne();

    }


    public static void jps() {
        Scanner sc = new Scanner(System.in);
        sc.next();
    }


    public static void jstack() {

        Map<Thread, StackTraceElement[]> tracks = Thread.getAllStackTraces();
        System.out.println(JSON.toJSON(tracks));

    }


    public static void jconsoleOne() {

        Scanner sc = new Scanner(System.in);
        sc.next();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        },"while true").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (JVMTools.class) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"wait").start();

    }




}
