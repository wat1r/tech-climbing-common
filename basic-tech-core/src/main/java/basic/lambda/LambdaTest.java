package basic.lambda;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdaTest {


    public static void main(String[] args) {
        filterMap();
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


}
