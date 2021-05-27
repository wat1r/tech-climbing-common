package basic.lambda;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaTest {


    public static void main(String[] args) {
//        filterMap();
        buildList();
    }


    public static void filterMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>() {
            {
                put(1, "1");
                put(2, "2");
                put(3, "3");
            }
        };
        List<Integer> fi = new ArrayList<Integer>() {{
            add(1);
        }};

        Map<Integer, String> collect = map.entrySet().stream().filter(e ->
                {
                    if (!CollectionUtils.isEmpty(fi)) {
                        return fi.contains(e.getKey());
                    }
                    return true;
                }
        ).collect((Collectors.toMap(Map.Entry::getKey, e -> e.getValue())));

        System.out.println("dd");

    }


    private static void buildList() {
        int size = 10;
        List<String> list = IntStream.range(0, size).mapToObj(k -> "?").collect(Collectors.toList());
        List<String> list1 = Collections.nCopies(size, "?");
        System.out.println("end");
    }


}
