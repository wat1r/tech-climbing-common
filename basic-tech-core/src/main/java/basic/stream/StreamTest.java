package basic.stream;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by FrankCooper
 * Date 2019/5/27 21:07
 * Description
 */
@Slf4j
public class StreamTest {


    public static void main(String[] args) {

//        testOne();
//        testTwo();
        testThree();
    }


    public static void testOne() {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("apple", "click"));
        lists.add(Arrays.asList("boss", "dig", "qq", "vivo"));
        lists.add(Arrays.asList("c#", "biezhi"));
        long count = lists.stream().flatMap(Collection::stream).filter(str -> str.length() > 2).count();

        System.out.println(count);
        System.out.println("-----------------");

        String[] dd = {"a", "b", "c"};
        Stream<String> stream = Arrays.stream(dd);
        stream.filter(str -> str.equals("a")).forEach(System.out::println);
    }

    public static void testTwo() {
        Integer[] dd = {1, 2, 3};
        Stream<Integer> stream = Arrays.stream(dd);
        stream.map(str -> Integer.toString(str)).forEach(System.out::println);


        String[] strs = {"aaa", "bbb", "ccc"};
        Arrays.stream(strs).map(str -> str.split("")).forEach(System.out::println);// Ljava.lang.String;@53d8d10a
        Arrays.stream(strs).map(str -> str.split("")).flatMap(Arrays::stream).forEach(System.out::println);// aaabbbccc
        Arrays.stream(strs).map(str -> str.split("")).flatMap(str -> Arrays.stream(str)).forEach(System.out::println);// aaabbbccc

        /**
         * 第一段输出代码里，我们先看map操作，通过上面对map的介绍，我们可以看到，map可以改变返回的Stream的泛型，str.split("")，根据空字符串分隔，
         * 返回的类型是一个数组，返回的流也是Stream<String[]>，而不是Stream<String>；在第二段代码中，数组的流，经过map操作，
         * 返回Stream<String[]>后，再经过flatMap，把数组通过Arrays.stream变成一个新的流，再返回到原来的流里；
         * 这样，两个流就合并成一个流；第三段代码，是第二段代码的，另一种写法；
         */
    }


    public static void testThree() {
        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length));

        //---------------
        Map<String, String> collect = Arrays.asList("a", "b", "c")
                .stream()
                .map(Function.identity()) // <- This,
                .map(str -> str)          // <- is the same as this.
                .collect(Collectors.toMap(
                        Function.identity(), // <-- And this,
                        str -> str));// <-- is the same as this.
        //---------------
        List list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int[] arrayOK = list.stream().mapToInt(i -> (int) i).toArray();

        //---------------
//        int[] arrayProblem = list.stream().mapToInt(Function.identity()).toArray();


        log.debug("end");
    }


}
