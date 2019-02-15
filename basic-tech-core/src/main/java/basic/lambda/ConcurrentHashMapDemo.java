package basic.lambda;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author:Cooper
 * @Date:2019/2/11 20:12
 */
public class ConcurrentHashMapDemo {
    private Map<Integer, Integer> cache = new ConcurrentHashMap<>(15);

    public static void main(String[] args) {
        ConcurrentHashMapDemo ch = new ConcurrentHashMapDemo();
        System.out.println(ch.fibonaacci(80));
    }

    public int fibonaacci(Integer i) {
        if (i == 0 || i == 1) {
            return i;
        }

        return cache.computeIfAbsent(i, (key) -> {
            System.out.println("fibonaacci : " + key);
            return fibonaacci(key - 1) + fibonaacci(key - 2);
        });
    }
}
