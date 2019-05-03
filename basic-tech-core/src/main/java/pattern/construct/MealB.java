package pattern.construct;

/**
 * Created by FrankCooper
 * Date 2019/5/3 17:39
 * Description
 */
public class MealB extends MealBuilder{

    public void buildDrink() {
        meal.setDrink("一杯柠檬果汁");
    }

    public void buildFood() {
        meal.setFood("三个鸡翅");
    }

}