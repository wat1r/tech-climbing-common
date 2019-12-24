package recode.exampleOne.orderStrategy.strategy.strategys.impl;

import org.springframework.stereotype.Component;
import recode.exampleOne.orderStrategy.strategy.annotation.HandlerOrderType;
import recode.exampleOne.orderStrategy.strategy.bean.Order;
import recode.exampleOne.orderStrategy.strategy.strategys.OrderStrategy;

/**
 * Created by FrankCooper
 * Date 2019/12/24 22:25
 * Description
 */


@Component
@HandlerOrderType(Order.HALF)
public class HalfOrderStrategy implements OrderStrategy {
    @Override
    public void handleOrder(Order order) {
        System.out.println("----处理半价订单----");
    }
}
