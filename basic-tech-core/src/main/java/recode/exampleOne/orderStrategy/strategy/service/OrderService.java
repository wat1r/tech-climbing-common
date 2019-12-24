package recode.exampleOne.orderStrategy.strategy.service;

import recode.exampleOne.orderStrategy.strategy.bean.Order;

/**
 * Created by FrankCooper
 * Date 2019/12/24 23:02
 * Description
 */

public interface OrderService {
    void handleOrder(Order order);
}
