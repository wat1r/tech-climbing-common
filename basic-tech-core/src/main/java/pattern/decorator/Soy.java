package pattern.decorator;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:19
 * Description
 */
public class Soy extends CondimentDecorator {
    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " , Soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }

}
