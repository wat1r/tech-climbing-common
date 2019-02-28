package algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
}
