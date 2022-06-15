package basic.collection;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author: wangzhou(Frank Cooper)
 * @date: 2022/1/6 19:31
 * @description:
 */
public class MapTest {

    static MapTest handler = new MapTest();

    public static void main(String[] args) {
//        handler.testOne();
//        handler.testTwo();
        handler.testMapKeyOrder();
    }

    @Data
    @AllArgsConstructor
    class Person {
        private Integer id;
        private String name;
        private Integer age;
    }

    private void testOne() {
        Map<Integer, Map<Integer, Person>> parentMap = new HashMap<>();
        Map<Integer, Person> alice = new HashMap<Integer, Person>() {{
            put(1, new Person(1, "Alice", 18));
        }};

        parentMap.put(1, alice);

        int idx = 1;


        int count = 3;
        while (count-- > 0) {
            Map<Integer, Person> curMap = parentMap.get(idx);
            for (Person p : curMap.values()) {
                if (p.getId() == 1) {
                    Map<Integer, Person> bob = new HashMap<Integer, Person>() {{
                        put(2, new Person(2, "Bob", 20));
                    }};
                    parentMap.put(2, bob);
                    idx = 2;
                }

                if (p.getId() == 2) {
                    Map<Integer, Person> candy = new HashMap<Integer, Person>() {{
                        put(3, new Person(3, "Candy", 19));
                    }};
                    parentMap.put(3, candy);
                    idx = 2;
                }
            }
        }


    }


    private void testTwo() {
        Map<Integer, Person> map = new HashMap<>();
        Person alice = new Person(1, "Alice", 19);
        map.put(1, alice);
        Person p = map.get(1);
        p.setName("Alice_1");
        System.out.println(map.toString());
    }


    private void testMapKeyOrder() {
        Map<String, String> tm = new HashMap<>();
        tm.put("fsol_gamesvrstate_glog_cooper_a", "测试2");
        tm.put("fsol_databaseerror_glog_cooper_a", "测试3");
        tm.put("swy_databaseerror_glog_cooper_a", "测试3");
        tm.put("MISC_TABLE", "测试1");
        tm.put("NISC_TABLE", "测试1");
        tm.put("SWY_DATABASEERROR_GLOG_COOPER_A", "测试1");

        System.out.println();
    }
}
