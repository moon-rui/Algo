package me.moon.algo.lru;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LRui
 * @date 2019-1-21 16:44
 */
public class LRUlinkedMap<K, V> {

    private int cacheSize;

    private LinkedHashMap<K, V> cacheMap;

    public LRUlinkedMap(int cacheSize) {
        this.cacheSize = cacheSize;

        cacheMap = new LinkedHashMap(16, 0.75F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (cacheSize + 1 == cacheMap.size()) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public V get(K key) {
        return cacheMap.get(key);
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
    }

    public List<Map.Entry<K, V>> getAll() {
        return new ArrayList<>(cacheMap.entrySet());
    }
}
