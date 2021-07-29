package pattern.decorator.two;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/28 20:41
 * @description:
 */
//具体的 Decorator， 这里就是调味品
public class Chocolate extends Decorator {

    public Chocolate(Drink obj) {
        super(obj);
        setDes(" Chocolate ");
        setPrice(3.0f); // 调味品 的价格
    }

}

