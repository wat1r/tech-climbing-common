package recode.exampleOne.orderStrategy.strategy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import recode.exampleOne.orderStrategy.strategy.strategys.OrderStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FrankCooper
 * Date 2019/12/24 22:37
 * Description
 */
@Component
public class HandlerOrderContext {

    @Autowired
    private ApplicationContext applicationContext;
    //存放所有策略类Bean的map
    public static Map<Integer, Class<OrderStrategy>> orderStrategyMap = new HashMap<>();


    public OrderStrategy getOrderStrategy(Integer type) {
        Class<OrderStrategy> strategyClass = orderStrategyMap.get(type);
        if(strategyClass==null) throw new IllegalArgumentException("没有对应的订单策略");
        //从容器中获取对应的策略Bean
        return applicationContext.getBean(strategyClass);
    }

}
