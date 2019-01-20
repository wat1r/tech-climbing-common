package lambda.stream;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by FrankCooper
 * Date 2019/1/20 23:10
 * Description
 */
@Slf4j
public class StreamTest {


    List<Property> properties = null;

    @Before
    public void init() {
        Property p1 = new Property("叫了个鸡", 1000, 500, 2);
        Property p2 = new Property("张三丰饺子馆", 2300, 1500, 3);
        Property p3 = new Property("永和大王", 580, 3000, 1);
        Property p4 = new Property("肯德基", 6000, 200, 4);
        properties = Arrays.asList(p1, p2, p3, p4);
    }


    @Test
    public void testOne() {
        Collections.sort(properties, (x, y) -> x.distance.compareTo(y.distance));
        String name = properties.get(0).name;
        log.info("距离我最近的店铺是:" + name);
    }


    @Test
    public void testTwo() {

        List<Property> resList =
                properties.stream()
                        .sorted(Comparator.comparingInt(x -> x.distance))
                        .limit(2)
                        .collect(Collectors.toList());
        log.info(JSON.toJSONString(resList));

        Map<String, Integer> resMap = properties.stream().collect(Collectors.toMap(Property::getName, Property::getPriceLevel));
        log.info(JSON.toJSONString(resMap));

        List<String> paralleList = properties.parallelStream()
                .filter(p -> p.priceLevel < 4)
                .sorted(Comparator.comparingInt(Property::getDistance))
                .map(Property::getName)
                .limit(2)
                .collect(Collectors.toList());
        log.info(JSON.toJSONString(paralleList));


    }


}
