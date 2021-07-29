package pattern.decorator.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/28 20:48
 * @description:
 */
public class Soy extends Decorator {

    public Soy(Drink obj) {
        super(obj);
        setDes(" Soy ");
        setPrice(1.5f);
    }


}
