package serailize.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        View view = om.readValue(s, View.class);
        System.out.println(view);
    }
}
