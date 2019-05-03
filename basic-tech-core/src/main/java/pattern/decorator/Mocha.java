package pattern.decorator;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:19
 * Description
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }

}