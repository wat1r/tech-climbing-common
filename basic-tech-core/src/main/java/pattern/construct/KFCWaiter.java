package pattern.construct;

/**
 * Created by FrankCooper
 * Date 2019/5/3 17:39
 * Description
 */
public class KFCWaiter {

    private MealBuilder mealBuilder;

    public void setMealBuilder(MealBuilder mealBuilder) {
        this.mealBuilder = mealBuilder;
    }

    public Meal construct() {

        mealBuilder.buildFood();

        mealBuilder.buildDrink();

        return mealBuilder.getMeal();
    }


}
