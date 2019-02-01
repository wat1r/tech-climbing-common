package basic.lambda;

import org.junit.Test;

import java.util.Optional;

/**
 * @Author:Cooper
 * @Date:2019/2/1 18:58
 */
public class OptionalTest {


    @Test
    public void testOne() {
        // Optional.of(T t)
        Optional<String> optional = Optional.of("321");
        // 下面这句代码会报NullPointerException
//        Optional<String> optional2 = Optional.of(null);

        // Optional.empty()
        Optional<Object> emptyOptional = Optional.empty();

        // Optional.ofNullable(T t)
        Optional<String> nullableOptional = Optional.ofNullable("123");
        // 下面这句代码不会报错，因为ofNullable(T t)创建Optional时允许t为null，相当于Optional.empty()
        Optional<Object> nullableOptional2 = Optional.ofNullable(null);
    }


    @Test
    public void testTwo() {
        Optional<String> optional = Optional.of("123");
        String str = optional.get();
        System.out.println(str);//输出123

    }

    @Test
    public void testThree() {
        Optional<String> optional = Optional.of("123");
        boolean b = optional.isPresent();
        System.out.println(b);// 输出true

        optional = Optional.ofNullable(null);
        b = optional.isPresent();
        System.out.println(b);// 输出false
    }


    @Test
    public void execute() {
        Optional<String> foo = Optional.of("foo");
        String newStr = foo.map(this::runIfExist)
                .orElse(runIfEmpty());
        System.out.println(newStr);
    }

    private String runIfEmpty() {
        System.out.println("only run if empty");
        return "empty";

    }

    private String runIfExist(String s) {
        System.out.println("only run if optional is filled");
        return s;
    }

    @Test
    public void executeOnceMore() {
        Optional<String> foo = Optional.of("foo");
        String newStr = foo.map(this::runIfExist)
                .orElseGet(() -> runIfEmpty());
        System.out.println(newStr);
    }


}
