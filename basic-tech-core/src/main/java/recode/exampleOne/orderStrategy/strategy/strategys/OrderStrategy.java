package recode.exampleOne.orderStrategy.strategy.strategys;

import recode.exampleOne.orderStrategy.strategy.bean.Order;

/**
 * Created by FrankCooper
 * Date 2019/12/24 22:22
 * Description
 */
//处理订单策略
public interface OrderStrategy {
    void  handleOrder(Order order);
}
