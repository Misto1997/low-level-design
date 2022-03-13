package com.lowleveldesign.cache;

import com.lowleveldesign.cache.cache_impl.Cache;
import com.lowleveldesign.cache.cache_impl.factory.CacheFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CacheRunner {

    public static void main(String[] args) {

        SpringApplication.run(CacheRunner.class, args);

        Cache<Integer, String> cache = new CacheFactory<Integer, String>().defaultCache(2);
        doOperations(cache);
    }

    private static void doOperations(Cache<Integer, String> cache) {

        //Insert 1 -> First Value to cache
        put(cache, 1, "First Value");
        //Print Value: First Value
        System.out.println(get(cache, 1));

        System.out.println("-------------------------------");

        //Insert 2 -> Second Value to cache
        put(cache, 2, "Second Value");
        //Print Value: Second Value
        System.out.println(get(cache, 2));

        System.out.println("-------------------------------");

        //Insert 3 -> Third Value to cache, evict Key: 1 from cache
        put(cache, 3, "Third Value");
        // return null as it got evicted from cache
        System.out.println(get(cache, 1));
        //Print Value: Third Value
        System.out.println(get(cache, 3));

        System.out.println("-------------------------------");

        // Print Value: Second Value, this will move Key: 2 to first place as per LRU algo.
        System.out.println(get(cache,2));
        //Insert 4 -> Fourth Value to cache
        put(cache, 4, "Fourth Value");
        // return null as it got evicted from cache
        System.out.println(get(cache, 3));
    }

    private static String get(Cache<Integer, String> cache, int key) {
        return cache.get(key);
    }

    private static void put(Cache<Integer, String> cache, int key, String value) {
        cache.put(key, value);
    }
}
