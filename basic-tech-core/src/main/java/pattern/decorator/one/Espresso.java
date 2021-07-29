package pattern.decorator.one;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:06
 * Description
 */
public class Espresso extends Beverage {
    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.05;
    }

}