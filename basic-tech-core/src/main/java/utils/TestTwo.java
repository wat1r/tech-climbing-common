package utils;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTwo {

    private static Logger LOGGER = LoggerFactory.getLogger(TestTwo.class);

    public static void main(String[] args) {
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.error("error");


        LOGGER.info(JSON.toJSONString(Lists.newArrayList("hello","test")));

    }

}
