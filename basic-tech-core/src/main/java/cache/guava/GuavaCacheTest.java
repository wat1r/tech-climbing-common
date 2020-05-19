package cache.guava;

import com.google.common.cache.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2020/5/18
 * @Author Frank Cooper
 * @Description
 */
public class GuavaCacheTest {
    /**
     * https://www.jianshu.com/p/38bd5f1cf2f2
     */
    static GuavaCacheTest handler = new GuavaCacheTest();

    public static void main(String[] args) throws ExecutionException {
//        handler.test1();
        handler.test2();
    }

    private void test1() throws ExecutionException {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            public String load(String key) throws Exception {
                Thread.sleep(1000); //休眠1s，模拟加载数据
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .build(loader);//在构建时指定自动加载器

        loadingCache.get("key1");
        loadingCache.get("key2");
        loadingCache.get("key3");
    }

    private void test2() {
        CacheLoader<String, String> loader = new CacheLoader<String, String>() {
            public String load(String key) throws Exception {
                Thread.sleep(1000);
                if ("key".equals(key)) return null;
                System.out.println(key + " is loaded from a cacheLoader!");
                return key + "'s value";
            }
        };

        RemovalListener<String, String> removalListener = removal -> System.out.println("[" + removal.getKey() + ":" + removal.getValue() + "] is evicted!");

        LoadingCache<String, String> testCache = CacheBuilder.newBuilder()
                .maximumSize(7)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(removalListener)
                .build(loader);

        for (int i = 0; i < 10; i++) {
            String key = "key" + i;
            String value = "value" + i;
            testCache.put(key, value);
            System.out.println("[" + key + ":" + value + "] is put into cache!");
        }

        System.out.println(testCache.getIfPresent("key6"));

        try {
            System.out.println(testCache.get("key"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
