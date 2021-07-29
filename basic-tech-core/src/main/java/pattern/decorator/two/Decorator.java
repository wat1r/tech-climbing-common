package pattern.decorator.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/28 20:42
 * @description:
 */
public class Decorator extends Drink {

    private Drink obj;

    public Decorator(Drink obj) {
        this.obj = obj;
    }

    @Override
    public float cost() {
        //getPrice是自己的价格
        return super.getPrice() + obj.cost();
    }

    @Override
    public String getDes() {
        return super.getDes() + " " + super.getPrice() + "&&" + obj.getDes();
    }
}
