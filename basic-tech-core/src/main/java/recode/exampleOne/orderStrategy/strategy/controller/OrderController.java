package recode.exampleOne.orderStrategy.strategy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recode.exampleOne.orderStrategy.strategy.bean.Order;
import recode.exampleOne.orderStrategy.strategy.service.OrderService;

/**
 * Created by FrankCooper
 * Date 2019/12/24 23:05
 * Description
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/handler/{type}")
    public void handleOrder(@PathVariable Integer type) {
        Order order = Order.build()
                .add("name", "微信订单")
                .add("price", 99.9)
                .add("type", type);
        orderService.handleOrder(order);
    }

}
