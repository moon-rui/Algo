package me.moon.algo;

import me.moon.algo.lru.LRUlinkedMap;
import org.junit.Test;

import java.util.Map;

/**
 * @author LRui
 * @date 2019-1-21 16:58
 */
public class LRUlinkedMapTest {

    @Test
    public void testInsert() {
        LRUlinkedMap<String, Integer> map = new LRUlinkedMap<>(3);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        for (Map.Entry<String, Integer> entry : map.getAll()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " --> ");
        }
        System.out.println();
        map.put("4", 4);
        for (Map.Entry<String, Integer> entry : map.getAll()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " --> ");
        }
    }

    @Test
    public void testGet() {
        LRUlinkedMap<String, Integer> map = new LRUlinkedMap<>(3);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);

        for (Map.Entry<String, Integer> entry : map.getAll()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " --> ");
        }
        System.out.println();
        map.get("1");
        for (Map.Entry<String, Integer> entry : map.getAll()) {
            System.out.print(entry.getKey() + ":" + entry.getValue() + " --> ");
        }
    }
}
