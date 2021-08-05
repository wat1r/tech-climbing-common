package basic.lambda;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LambdaTest {


    public static void main(String[] args) {
//        filterMap();
//        buildList();
//        forEachMap();
        removeItem();
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


    private static void forEachMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("ddd");
        }
        map.forEach((k, v) -> System.out.println(k));
    }

    private static void removeItem() {
        HashMap<Integer, String> map = new HashMap<Integer, String>() {
            {
                put(1, "1");
                put(2, "2");
                put(3, "3");
            }
        };
//        map.forEach((k, v) -> {
//            if (k == 1) map.remove(k);
//        });
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey() == 1) it.remove();
        }
        map.forEach((k, v) -> System.out.printf("%d->%s\n", k, v));
    }


}
