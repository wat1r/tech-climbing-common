package basic.jvm;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
//        THREAD_POOL_EXECUTOR =‘ new ThreadPoolExecutor(coreNum * 3, coreNum * 4, 10, TimeUnit.MINUTES,
//                ARRAY_BLOCKING_QUEUE, new ThreadFactoryBuilder().setNameFormat("chemical-data-automation-db-%d").build(), new ThreadPoolExecutor.AbortPolicy());
//        FAST_READ_FILE_EXECUTOR = new ThreadPoolExecutor(coreNum, coreNum + 1, 10, TimeUnit.MINUTES,
//                ARRAY_READ_BLOCKING_QUEUE, new ThreadFactoryBuilder().setNameFormat("chemical-data-automation-read-%d").build(), new ThreadPoolExecutor.AbortPolicy());
    }



    @Test
    public void testRuntime(){
        System.out.println(""+Runtime.getRuntime().availableProcessors());
    }









}
