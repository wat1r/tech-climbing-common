package pattern.decorator.one;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:06
 * Description
 */
public class Decat extends Beverage {
    public Decat() {
        description = "Decat";
    }

    @Override
    public double cost() {
        return 0.99;
    }

}
