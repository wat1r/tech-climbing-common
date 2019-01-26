package undefined;

import cglib.beanCopier.OrderDto;
import cglib.beanCopier.OrderEntity;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:Cooper
 * @Date:2019/1/26 13:38
 */
@Slf4j
public class ReverseProxyTest {

    @Test
    public void testOne() throws ClassNotFoundException {

        Map<String, Object> params = new HashMap<String, Object>() {{
            put("ORDER_ENTITY", "cglib.beanCopier.OrderEntity");
            put("ORDER_DTO", "cglib.beanCopier.OrderDto");
        }};

        Class<OrderEntity> orderEntity = (Class<OrderEntity>) Class.forName(params.get("ORDER_ENTITY").toString());
        Class<OrderDto> orderDto = (Class<OrderDto>) Class.forName(params.get("ORDER_DTO").toString());


        log.info("orderEntity is --->:{}", JSON.toJSONString(orderEntity));
        log.info("orderDto is --->:{}", JSON.toJSONString(orderDto));


        log.info(String.format("----------->name:%s", JSON.toJSON(params)));

    }
}
