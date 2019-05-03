package pattern.decorator;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:04
 * Description
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 0.89;
    }

}
