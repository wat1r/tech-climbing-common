package pattern.construct;

/**
 * Created by FrankCooper
 * Date 2019/5/3 17:36
 * Description
 */
public abstract class MealBuilder {


    Meal meal = new Meal();

    public abstract void buildFood();

    public abstract void buildDrink();


    public Meal getMeal() {
        return meal;
    }
}
