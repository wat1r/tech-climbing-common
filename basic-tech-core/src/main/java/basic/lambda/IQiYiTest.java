package basic.lambda;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by FrankCooper
 * Date 2019/5/21 22:09
 * Description
 * java1.8 新特性（五 如何使用filter，limit ，skip ，distinct map flatmap ，collect 操作 java集合）
 */
public class IQiYiTest {

    public static void main(String[] args) {
//        testOne();
//        testTwo();
//        testThree();
//        testFour();
//        testFive();
//        testSix();
//        testSeven();
        testEight();


    }


    /**
     * Stream<T> filter(Predicate<? super T> predicate) 接受的是一个 predicate 类型的接口参数。
     * predicate 有一个test 的抽象boolean类型的返回值方法。该方法满足条件age>=15 返回true 则程序 向下执行，执行结果：
     */
    public static void testOne() {
        // 1.集合创建stream对象
        List<User> asList = Arrays.asList(new User("张三", 15), new User("李四", 25), new User("Tom", 10));
        Stream<User> streamList = asList.stream();
        // 找出 user age>15 的user collect(Collectors.toList()) 返回一个对应的集合
        List<User> resultList = streamList.filter(user -> user.getAge() >= 15).collect(toList());
        resultList.forEach(System.out::println);

    }

    public static void testTwo() {
        List<Integer> asList = Arrays.asList(1, 2, 3, 5, 6, 7, 8, 8, 9, 7);
        List<Integer> collect = asList.stream().distinct().collect(toList());
        collect.forEach(System.out::println);
    }


    /**
     * 使用map 將集合中的每个数字 扩大 两倍 ：
     */
    public static void testThree() {
        List<Integer> asList = Arrays.asList(1, 2, 3, 5, 6, 7, 8, 9);
        List<Integer> collect = asList.stream().map(i -> i * 2).collect(toList());
        collect.forEach(System.out::println);
    }


    /**
     * 解决一个字符串数组  返回单一的字符串使用flatMap：
     */
    public static void testFour() {
        List<String> list = Arrays.asList("AABBBACCDED", "EDADCAJIONG ");
        List<String> collect = list.stream().flatMap(item -> Arrays.stream(item.split(""))).distinct().collect(toList());
        collect.forEach(System.out::println);
    }

    /**
     * jdk8 Stream map和flatMap区别
     */
    public static void testFive() {
        List<String> collected = Stream.of("a", "b").collect(toList());
        List<Integer> figure = collected.stream().map(s -> {
            Integer i;
            switch (s) {
                case "a":
                    i = 1;
                    break;
                case "b":
                    i = 2;
                    break;
                default:
                    i = -1;
                    break;
            }
            return i;
        }).collect(toList());
        System.out.println(JSON.toJSONString(figure));

        System.out.println("--------------");
        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(2);
        List<Integer> b = new ArrayList<>();
        b.add(3);
        b.add(4);
        List<Integer> figures = Stream.of(a, b).flatMap(u -> u.stream()).collect(toList());
        figures.forEach(f -> System.out.println(f));

    }


    public static void testSix() {
        Arrays.asList("helloword", "hello").stream().map(t -> t.split("")).flatMap(t -> {
            return Arrays.asList(t).stream();
        }).forEach(System.out::println);
    }

    public static void testSeven() {
        List<String> words = new ArrayList<String>();
        words.add("your");
        words.add("name");
        Stream<Stream<Character>> result = words.stream().map(w -> characterStream(w));
        Stream<Character> letters = words.stream().flatMap(w -> characterStream(w));
    }


    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray())
            result.add(c);
        return result.stream();
    }

    public static void testEight() {
        String[] words = new String[]{"Hello", "World"};
        List<String[]> a = Arrays.stream(words)
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        a.forEach(System.out::print);
    }


    static class User {
        String name;
        Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
