package me.moon.algo;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author LRui
 * @date 2019-1-16 14:08
 */
public class LinkLoopTest {

    @Test
    public void test1() {
        LinkLoop.Node n1 = new LinkLoop.Node(1);
        LinkLoop.Node n2 = new LinkLoop.Node(2);
        LinkLoop.Node n3 = new LinkLoop.Node(3);
        n1.next = n2;
        n2.next = n3;

        boolean loop = LinkLoop.isLoop(n1);
        Assert.assertFalse(loop);
    }

    @Test
    public void test2() {
        LinkLoop.Node n1 = new LinkLoop.Node(1);
        LinkLoop.Node n2 = new LinkLoop.Node(2);
        LinkLoop.Node n3 = new LinkLoop.Node(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        boolean loop = LinkLoop.isLoop(n1);
        Assert.assertTrue(loop);
    }

    @Test
    public void test3() {
        LinkLoop.Node n1 = new LinkLoop.Node(1);
        LinkLoop.Node n2 = new LinkLoop.Node(2);
        n1.next = n2;

        boolean loop = LinkLoop.isLoop(n1);
        Assert.assertFalse(loop);
    }
}
