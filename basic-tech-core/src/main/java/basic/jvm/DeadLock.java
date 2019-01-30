package basic.jvm;

/**
 * Created by FrankCooper
 * Date 2019/1/30 21:53
 * Description
 */
public class DeadLock implements Runnable {
    private Object obj1;
    private Object obj2;

    public DeadLock(Object obj1, Object obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }

    @Override
    public void run() {
        synchronized (obj1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj2) {
                System.out.println("hello deadlock");
            }
        }
    }
}


class TestDeadLock {
    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread(new DeadLock(obj1, obj2)).start();
        new Thread(new DeadLock(obj2, obj1)).start();
    }
}