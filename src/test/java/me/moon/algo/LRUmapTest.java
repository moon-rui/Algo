package me.moon.algo;

import me.moon.algo.lru.LRUmap;
import org.junit.Test;

/**
 * @author LRui
 * @date 2019-1-21 16:32
 */
public class LRUmapTest {

    @Test
    public void testInsert() {
        LRUmap<String, Integer> lrUmap = new LRUmap<>(3);
        lrUmap.put("1", 1);
        lrUmap.put("2", 2);
        lrUmap.put("3", 3);

        System.out.println(lrUmap.toString());
        lrUmap.put("4", 4);
        System.out.println(lrUmap.toString());
        lrUmap.put("5", 5);
        System.out.println(lrUmap.toString());
    }

    @Test
    public void testGet() {
        LRUmap<String, Integer> lrUmap = new LRUmap<>(3);
        lrUmap.put("1", 1);
        lrUmap.put("2", 2);
        lrUmap.put("3", 3);

        System.out.println(lrUmap.toString());
        lrUmap.get("1");
        System.out.println(lrUmap.toString());
        lrUmap.get("2");
        System.out.println(lrUmap.toString());
    }
}
