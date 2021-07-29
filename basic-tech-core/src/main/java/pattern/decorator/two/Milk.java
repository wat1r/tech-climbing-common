package pattern.decorator.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/28 20:47
 * @description:
 */
public class Milk extends Decorator {
    public Milk(Drink obj) {
        super(obj);
        setDes(" Milk ");
        setPrice(2.0f);
    }

}
