package com.my.ds.code.cache.lru.demo;

import com.my.ds.code.cache.lru.impl.LRUCache;

public class LRUDemo {

    // Demo main
    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(3);

        cache.put(1, "one");
        cache.put(2, "two");
        cache.put(3, "three");
        cache.display(); // MRU: 3,2,1

        System.out.println("get(2) -> " + cache.get(2));
        cache.display(); // MRU: 2,3,1

        cache.put(4, "four"); // evicts key 1 (LRU)
        cache.display(); // MRU: 4,2,3

        System.out.println("remove(3) -> " + cache.remove(3));
        cache.display(); // MRU: 4,2

        cache.put(5, "five");
        cache.put(6, "six"); // evicts key 2
        cache.display(); // MRU: 6,5,4

        System.out.println("size = " + cache.size());
        System.out.println("get(2) -> " + cache.get(2)); // null (evicted)
        System.out.println("get(4) -> " + cache.get(4)); // "four", moves 4 to head
        cache.display(); // MRU: 4,6,5

        cache.clear();
        System.out.println("after clear, empty? " + cache.isEmpty());
    }
}
