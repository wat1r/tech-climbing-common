package collection.guava;

import com.google.common.collect.Sets;

import java.util.Set;

public class GuavaDemoOne {

    static GuavaDemoOne handler = new GuavaDemoOne();


    public static void main(String[] args) {
        handler.testOne();
    }

    private void testOne() {
        // Creating first set named set1
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5, 6);

        // Creating second set named set2
        Set<Integer> set2 = Sets.newHashSet(1, 3, 5, 7);

        // Using Guava's Sets.difference() method
        Set<Integer> diff = Sets.difference(set1, set2);

        // Displaying the unmodifiable view of
        // the difference of two sets.
        System.out.println("Set 1:"
                + set1);
        System.out.println("Set 2:"
                + set2);
        System.out.println("Difference between "
                + "Set 1 and Set 2:"
                + diff);
    }
}
