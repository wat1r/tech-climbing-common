package basic.concurrent;

/**
 * @Author Frank Cooper(wang zhou)
 * @Date: 2024/03/29/ 15:06
 * @description
 */
public class EchoServer implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("This is a echo server. The current time is " +
                System.currentTimeMillis() + ".");
    }
}