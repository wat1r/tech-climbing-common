package pattern.construct;

/**
 * Created by FrankCooper
 * Date 2019/5/3 17:38
 * Description
 */
public class MealA extends MealBuilder {
    @Override
    public void buildFood() {
        meal.setFood("一盒薯条");
    }


    @Override
    public void buildDrink() {
        meal.setDrink("一杯可乐");
    }
}
