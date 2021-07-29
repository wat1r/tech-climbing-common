package pattern.decorator.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/28 20:41
 * @description:
 */
public class Coffee extends Drink {
    @Override
    public float cost() {
        return super.getPrice();
    }
}
