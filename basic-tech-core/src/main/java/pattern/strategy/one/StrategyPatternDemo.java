package pattern.strategy.one;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/11/26 8:10
 * @description:
 */
public class StrategyPatternDemo {

    public static void main(String[] args) {
        int type = 1;
        Context context = new Context(DiscountDefaultStrategyFactory.getDiscountStrategy(type));
        context.calculate();
    }

    public interface DiscountStrategy {
        public void calculate();
    }

    public static class DiscountStrategyA implements DiscountStrategy {
        public void calculate() {
            System.out.println("按优化计价方式1的负责业务逻辑");
        }

        ;
    }

    public static class DiscountStrategyB implements DiscountStrategy {
        public void calculate() {
            System.out.println("按优化计价方式2的负责业务逻辑");
        }

        ;
    }

    public static class DiscountStrategyC implements DiscountStrategy {
        public void calculate() {
            System.out.println("按优化计价方式3的负责业务逻辑");
        }

        ;
    }

    public static class DiscountDefaultStrategyC implements DiscountStrategy {
        public void calculate() {
            System.out.println("按默认计价方式的负责业务逻辑");
        }

        ;
    }

    public static class DiscountDefaultStrategyFactory {
        public static DiscountStrategy getDiscountStrategy(int type) {
            if (type == 1) {
                return new DiscountStrategyA();
            } else if (type == 2) {
                return new DiscountStrategyB();
            } else if (type == 3) {
                return new DiscountStrategyC();
            } else {
                return new DiscountDefaultStrategyC();
            }
        }

    }

    public static class Context {
        //持有一个具体策略的对象
        private DiscountStrategy strategy;

        /**
         * 构造函数，传入一个具体策略对象
         *
         * @param strategy 具体策略对象
         */
        public Context(DiscountStrategy strategy) {
            this.strategy = strategy;
        }

        /**
         * 策略方法
         */
        public void calculate() {
            strategy.calculate();
        }

    }

}
