package basic.jvm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by FrankCooper
 * Date 2019/1/26 21:57
 * Description
 */
@Slf4j
public class JVMTest {


    @Test
    public void testOne() {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");

        log.info("s1 == s2:{}", s1 == s2);//true
        log.info("s1 == s3:{}", s1 == s3);//false
        log.info("s1 == s3.intern:{}", s1 == s3.intern());//true
    }

    @Test
    public void testTwo() {
        User user = new User();
        log.info(user.toString());
    }



    @Test
    public void referenceTest(){

    }








}
