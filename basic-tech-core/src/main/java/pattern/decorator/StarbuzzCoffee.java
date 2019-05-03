package pattern.decorator;

/**
 * Created by FrankCooper
 * Date 2019/5/3 18:20
 * Description
 */
public class StarbuzzCoffee {

    /**
     * Espresso $1.05
     * DarkRoast , Mocha , Mocha , Whip $1.65
     * @param args
     */
    public static void main(String[] args) {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage2 = new DarkRoast();
        beverage2 = new Mocha(beverage2);
        beverage2 = new Mocha(beverage2);
        beverage2 = new Whip(beverage2);
        System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
    }

}
