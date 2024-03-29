package basic.collection;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

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
//        handler.testMapKeyOrder();
//        handler.testFour();
//        handler.testFive();
        handler.testSix();
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
        tm.put("dd_cooper_a", "测试2");
        tm.put("cc_cooper_a", "测试3");
        tm.put("ee_cooper_a", "测试3");
        tm.put("MISC_TABLE", "测试1");
        tm.put("NISC_TABLE", "测试1");
        tm.put("ff_COOPER_A", "测试1");

        System.out.println();
    }

    private void testFour() {
        Map<Integer, Set<Integer>> map = new HashMap<>();
//        Set<Integer> set = map.getOrDefault(1, new HashSet<>());
//        set.add(2);
//        set.add(3);
//        map.put(1,set);
        map.putIfAbsent(1, new HashSet<>());
        System.out.println();
    }

    private void testFive() {
        Long key = 545403706309347072L;
        WorkflowInstanceDAGVO dagvo = new WorkflowInstanceDAGVO();
        List<Map<Long, List<Long>>> adjMapsList = new ArrayList<>();

        Map<Long, List<Long>> m1 = new HashMap<>();
        List<Long> l1 = new ArrayList<Long>() {{
            add(545705697296254720L);
            add(545796293683840768L);
            add(545584885796964096L);
            add(545394144227362560L);
        }};
        m1.put(key, l1);
        adjMapsList.add(m1);

        Map<Long, List<Long>> m2 = new HashMap<>();
        List<Long> l2 = new ArrayList<Long>() {{
            add(545403704669374208L);
            add(545766093910705920L);
        }};
        m2.put(key, l2);
        adjMapsList.add(m2);

        for (Map<Long, List<Long>> list : adjMapsList) {
            dagvo.addAdjMap(list);
        }


        System.out.println(JSONObject.toJSONString(dagvo));
    }


    public void testSix() {
        first:
        for (int i = 0; i < 5; i++) { // 第一层循环
            System.out.println("Outer loop: " + i);
            second:
            for (int j = 0; j < 5; j++) { // 第二层循环
                System.out.println("Middle loop: " + j);
                third:
                for (int k = 0; k < 5; k++) { // 第三层循环
                    if (k == 1) {
                        System.out.println("third loop: " + k);
                        break second;
                    }

                }

            }
            System.out.println();

            // 重置标志，以便下次外层循环迭代
        }

        System.out.println("Exited all loops");
    }
}
