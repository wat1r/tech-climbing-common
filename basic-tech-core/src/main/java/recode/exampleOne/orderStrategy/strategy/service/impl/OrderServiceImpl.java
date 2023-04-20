package recode.exampleOne.orderStrategy.strategy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import recode.exampleOne.orderStrategy.strategy.bean.Order;
import recode.exampleOne.orderStrategy.strategy.config.HandlerOrderContext;
import recode.exampleOne.orderStrategy.strategy.service.OrderService;
import recode.exampleOne.orderStrategy.strategy.strategys.OrderStrategy;

/**
 * Created by FrankCooper
 * Date 2019/12/24 23:03
 * Description
 */
@Component
public class OrderServiceImpl implements OrderService {


    @Autowired
    HandlerOrderContext handlerOrderContext;


    @Override
    public void handleOrder(Order order) {
        //使用策略处理订单
        OrderStrategy orderStrategy = handlerOrderContext.getOrderStrategy(order.getType());
        orderStrategy.handleOrder(order);
    }
}
