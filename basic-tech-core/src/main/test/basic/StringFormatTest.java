package basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @Author:Cooper
 * @Date:2019/1/26 13:29
 */
@Slf4j
public class StringFormatTest {


    @Test
    public void testOne() {
        String name = "hello";
        log.info(String.format("%1$10s", name));
        log.info(String.format("%1$-10s", name));

    }


}
