package recode.exampleOne.orderStrategy.strategy.annotation;

import java.lang.annotation.*;

/**
 * Created by FrankCooper
 * Date 2019/12/24 21:59
 * Description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerOrderType {
    /**
     * 策略类型
     * @return
     */
    int value();
}
