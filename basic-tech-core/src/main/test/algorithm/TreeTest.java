package algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by FrankCooper
 * Date 2019/2/28 21:40
 * Description
 */
public class TreeTest {

    @Test
    public void testOne() {

        List<List<Integer>> resList = new ArrayList<>();
        for (int i = 0; i != 5; i++) {
            int index = i;
            List<Integer> levelList = new ArrayList<Integer>() {{
                add(index);
            }};
            resList.add(0, levelList);
        }
    }

    @Test
    public void testTreeMapOne() {

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(5, "zuo");
        treeMap.put(10, "cheng");
        treeMap.put(25, "laowang");
        treeMap.put(15, "yun");
        treeMap.put(20, "yao");
        System.out.println(treeMap.containsKey(5));
        System.out.println(treeMap.get(5));
        System.out.println(treeMap.firstKey());
        System.out.println(treeMap.lastKey());
        System.out.println(treeMap.ceilingKey(12));
        System.out.println(treeMap.floorKey(12));

    }
}
