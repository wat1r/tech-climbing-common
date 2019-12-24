package recode.exampleOne.orderStrategy.strategy.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import recode.exampleOne.orderStrategy.strategy.annotation.HandlerOrderType;
import recode.exampleOne.orderStrategy.strategy.strategys.OrderStrategy;

import java.util.Map;

/**
 * Created by FrankCooper
 * Date 2019/12/24 22:57
 * Description
 */
//策略核心功能,获取所有策略注解的类型
@Component
public class HandlerOrderProcessor implements ApplicationContextAware {
    // 获取所有的策略Beanclass 加入HandlerOrderContext属性中
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Object> orderStrategyMap = applicationContext.getBeansWithAnnotation(HandlerOrderType.class);
        orderStrategyMap.forEach((k, v) -> {
            Class<OrderStrategy> orderStrategyClass = (Class<OrderStrategy>) v.getClass();
            int type = orderStrategyClass.getAnnotation(HandlerOrderType.class).value();
            HandlerOrderContext.orderStrategyMap.put(type, orderStrategyClass);
        });


    }
}
