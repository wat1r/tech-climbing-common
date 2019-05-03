package pattern.construct;

/**
 * Created by FrankCooper
 * Date 2019/5/3 17:41
 * Description
 */
public class Client {

    public static void main(String[] args) {
        KFCWaiter waiter = new KFCWaiter();
        MealA a = new MealA();
        waiter.setMealBuilder(a);
        Meal mealA = waiter.construct();
        System.out.print("套餐A的组成部分:");
        System.out.println(mealA.getFood() + "---" + mealA.getDrink());
    }
}


