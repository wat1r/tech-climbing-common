package pattern.decorator.two;

import lombok.Data;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2021/7/28 20:38
 * @description:
 */
@Data
public abstract class Drink {

    public String des;//描述
    private float price = 0.0f;

    //计算费用的抽象方法:子类来实现
    public abstract float cost();
}
