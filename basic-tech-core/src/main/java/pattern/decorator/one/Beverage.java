package pattern.decorator.one;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:03
 * Description
 */
public abstract class Beverage {
    protected String description = "Unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
