package serailize.jackson;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/9/11
 * @Author Frank Cooper
 * @Description
 */
public class TestJackson {
    public static void main(String[] args) throws IOException {
//        test1();

//        test2();

        Long id = 210L;
        System.out.println(id== 210);

    }

    private static void test1() throws IOException {
        View v = new View();


        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(3, 6);

        List<Shape> list = new ArrayList<>();
        list.add(circle);
        list.add(rectangle);
        v.setShapes(list);

        System.out.println("-- serializing --");
        ObjectMapper om = new ObjectMapper();
        String s = om.writeValueAsString(v);
        System.out.println(s);

        System.out.println("-- deserializing --");
//        View view = om.readValue(s, View.class);
//        System.out.println(view);

//        JsonNode jsonNode = om.readTree();
//
//        System.out.printf("");
    }


    private static void test2() {
        List<Person> list = Lists.newArrayList();
        list.add(new Person("张三", 18));
        list.add(new Person("张三", 18));
        list.add(new Person("张三", 18));
        list.add(new Person("张三         电话的    ", 18));
        list.add(new Person("张三" +
                "" +
                "的的             的的" +
                " " +
                "" +
                "\n" +
                "这是", 18));
        list.add(new Person("张三", 18));
        list.add(new Person("张三", 18));
        list.add(new Person("张三", 18));
        String s = JSON.toJSONString(list);
        String s1 = StringEscapeUtils.unescapeEcmaScript(s);
        System.out.println(s1);
        System.out.println("---------------");
        System.out.println(JSON.toJSONString(list));


    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person {
        private String name;
        private Integer age;
    }


}
