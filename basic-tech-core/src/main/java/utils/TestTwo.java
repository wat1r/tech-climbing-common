package utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTwo {

    private static Logger LOGGER = LoggerFactory.getLogger(TestTwo.class);

    public static void main(String[] args) {
        LOGGER.debug("debug");
        LOGGER.info("info");
        LOGGER.error("error");


    }

}
