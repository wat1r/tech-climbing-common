package cache.guava;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.*;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
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
     * https://www.cnblogs.com/fnlingnzb-learner/p/11022152.html
     */
    static GuavaCacheTest handler = new GuavaCacheTest();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        handler.test1();
//        handler.test2();
//        handler.test3();
//        handler.test4();
//        handler.test5();
        handler.test6();
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

    private void test3() throws InterruptedException {

        Cache<String, String> cache = CacheBuilder.newBuilder()
//                .maximumSize(2)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
        cache.put("key3", "value3");
        int time = 1;
        while (true) {
            Thread.sleep(time * 1000);
            int t = ++time;
            System.out.println("睡眠" + t + "秒后取到key1的值为：" + cache.getIfPresent("key1"));
            System.out.println("睡眠" + t + "秒后取到key2的值为：" + cache.getIfPresent("key2"));
            System.out.println("睡眠" + t + "秒后取到key3的值为：" + cache.getIfPresent("key3"));
        }
    }

    private void test4() {
        Cache<String, String> cache = CacheBuilder.newBuilder()
//                .maximumSize(3)
                .recordStats() //开启统计信息开关
                .build();
        cache.put("key1", "value1");
        cache.put("key2", "value2");
//        cache.put("key3", "value3");
        cache.put("key4", "value4");
//        cache.put("key1", "value+++");
//        cache.put("key1", "value========");
        System.out.println(cache.size());

        System.out.println(JSON.toJSONString(cache));
        System.out.println(cache.stats());
//        cache.getIfPresent("key1");
//        cache.getIfPresent("key2");
//        cache.getIfPresent("key3");
//        cache.getIfPresent("key4");
//        cache.getIfPresent("key5");
//        cache.getIfPresent("key6");

        System.out.println(cache.stats()); //获取统计信息

    }


    private void test5() throws InterruptedException {
        Map<Long, Cache<Long, String>> cacheMap = new ConcurrentHashMap<>();
        Cache<Long, String> cache = cacheMap.getOrDefault(210L, CacheBuilder.newBuilder()
                .expireAfterWrite(3, TimeUnit.SECONDS)
                .weakValues()
                .recordStats() //开启统计信息开关
                .build());
        cache.put(100010L, "value1");
        cache.put(100011L, "value2");
        cache.put(100012L, "value3");
        cache.put(100013L, "value4");
        cacheMap.put(210L, cache);

//        ConcurrentMap<Long, String> map = cache.asMap();


        Cache<Long, String> cm = cacheMap.get(210L);
        cm.invalidate(100014L);

        ConcurrentMap<Long, String> map = cache.asMap();

        System.out.println(JSON.toJSONString(map));


//        int time = 1;
//        while (true) {
//            Thread.sleep(time * 1000);
//            int t = time;
//            System.out.println(cacheMap.size());
//            System.out.println(cache.size());
//            for (long i = 100010L; i <= 100013L; ++i) {
//                System.out.println("睡眠" + t + "秒后取到" + i + "的值为：" + cache.getIfPresent(i));
//            }
//            ++time;
//            cache.cleanUp();
//        }


    }


    public void test6() {
        Cache<Long, Set<Long>> counter = CacheBuilder.newBuilder().expireAfterAccess(1, TimeUnit.HOURS).build();
        put(counter, 552656521549317888L, 1624L);
        put(counter, 552656521549317888L, 1625L);
        put(counter, 552279701758936832L, 1625L);
        ConcurrentMap<Long, Set<Long>> result = counter.asMap();
        for (Map.Entry<Long, Set<Long>> e : result.entrySet()) {
            Set<Long> v = e.getValue();
            v.remove(1624L);
        }
        System.out.println(JSONObject.toJSONString(counter));


    }


    public synchronized void put(Cache<Long, Set<Long>> counter, Long wfInstanceId, Long batchId) {
        Set<Long> batchIdSet = counter.getIfPresent(wfInstanceId);
        if (batchIdSet == null) {
            batchIdSet = Sets.newHashSet();
        }
        batchIdSet.add(batchId);
        counter.put(wfInstanceId, batchIdSet);
    }

    @Data
    @AllArgsConstructor
    public static class Limiter {
//        private
    }

}
