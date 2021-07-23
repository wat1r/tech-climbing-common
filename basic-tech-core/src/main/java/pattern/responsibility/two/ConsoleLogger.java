package pattern.responsibility.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/21 20:02
 * @description:
 */
public class ConsoleLogger extends AbstractLogger {

    public ConsoleLogger(int level){
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}
