package pattern.decorator.one;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:04
 * Description
 */
public class DarkRoast extends Beverage {
    public DarkRoast(){
        description = "DarkRoast";
    }
    @Override
    public double cost() {
        return 1.05;
    }

}
