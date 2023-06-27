package basic.collection;

import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2020/6/28
 * @Author Frank Cooper
 * @Description
 */
public class TestList {

    static TestList handler = new TestList();

    public static void main(String[] args) {

//        handler.testOne();

//        handler.testTwo();
//        handler.testThree();
        handler.testFour();
    }


    private void testOne() {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("5");
        list1.add("6");
        list1.add("7");
        list1.add("8");

        List<String> list2 = new ArrayList<String>();
        list2.add("2");
        list2.add("7");
        list2.add("10");

        System.out.println("-----");

        //交集
        List<String> collect1 = list1.stream().filter(num -> list2.contains(num))
                .collect(Collectors.toList());
        System.out.println("交集");
        collect1.stream().forEach(System.out::println);

        //差集 list1-list2
        List<String> collect2 = list1.stream().filter(num -> !list2.contains(num))
                .collect(Collectors.toList());
        System.out.println("差集list1-list2");
        collect2.stream().forEach(System.out::println);

        //差集list2-list1
        List<String> collect3 = list2.stream().filter(num -> !list1.contains(num))
                .collect(Collectors.toList());
        System.out.println("差集list2-list1");
        collect3.stream().forEach(System.out::println);

        //并集  不去重
        list1.addAll(list2);
        System.out.println("并集  不去重");
        list1.stream().forEach(System.out::println);

        //并集  去重
        List<String> collect4 = list1.stream().distinct().collect(Collectors.toList());
        System.out.println("并集  去重");
        collect4.stream().forEach(System.out::println);
    }


    public void testTwo() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                iterator.remove();   //注意这个地方
        }
    }


    public void testThree() {
        Set<String> set1 = new HashSet<>(Arrays.asList("A"));
        Set<String> set2 = new HashSet<>(Arrays.asList("A", "B"));
        System.out.println(set1.retainAll(set2));//false
        System.out.println(set2.retainAll(set1));//true
        set1 = new HashSet<>(Arrays.asList("A", "B"));
        set2 = new HashSet<>(Arrays.asList("A", "B"));
        System.out.println(set1.retainAll(set2));//false
        System.out.println(set2.retainAll(set1));//false
        set1 = new HashSet<>(Arrays.asList("A"));
        set2 = new HashSet<>(Arrays.asList("B"));
        System.out.println(set1.retainAll(set2));//true
        System.out.println(set2.retainAll(set1));//true
    }

    public void testFour() {
        List<Integer> l1 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        List<Integer> l2 = new ArrayList<>(l1);
        l2.remove(2);
        System.out.println(JSONObject.toJSONString(l1));
        System.out.println(JSONObject.toJSONString(l2));

    }

}


