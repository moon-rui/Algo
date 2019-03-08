package me.moon.algo.lru;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.AbstractMap;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author LRui
 * @date 2019-1-17 11:24
 */
public class LRUabstractMap extends AbstractMap {

    /**
     * thread pool for time out check
     */
    private ExecutorService checkTimePool;

    private static final int MAX_SIZE = 10;

    private static final ArrayBlockingQueue<Node> QUEUE = new ArrayBlockingQueue<>(MAX_SIZE);

    private static final int DEFAULT_ARRAY_SIZE = 3;

    /**
     * 判断是否停止
     */
    private volatile boolean flag = true;

    private static final Long EXPIRE_TIME = 6000L;

    /**
     * size of map
     */
    private volatile AtomicInteger size;

    private int arraySize;

    private Object[] array;

    public LRUabstractMap() {
        arraySize = DEFAULT_ARRAY_SIZE;
        array = new Object[arraySize];

        executeCheckTime();
    }

    private void executeCheckTime() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("check-thread-%d").setDaemon(true).build();
        checkTimePool = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        checkTimePool.execute(new CheckTimeThread());
    }

    @Override
    public Set<Entry> entrySet() {
        return super.keySet();
    }

    @Override
    public Object put(Object key, Object value) {
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) array[index];

        if (currentNode == null) {
            array[index] = new Node(null, null, key, value);

            QUEUE.offer((Node) array[index]);

            sizeUp();
        } else {
            Node nNode = currentNode;
            while (nNode != null) {
                if (nNode.key == key) {
                    nNode.value = value;
                }
                nNode = nNode.next;
            }

        }
        return null;
    }

    @Override
    public Object get(Object key) {
        return super.get(key);
    }

    @Override
    public Object remove(Object key) {
        int hash = hash(key);
        int index = hash % arraySize;
        Node currentNode = (Node) array[index];

        if (currentNode == null) {
            return null;
        }

        if (currentNode.key == key) {

        }

        return super.remove(key);
    }

    private void sizeUp() {
        // 在put的时候认为里面已经有数据
        flag = true;

        if (size == null) {
            size = new AtomicInteger();
        }

        int size = this.size.incrementAndGet();
        if (size >= MAX_SIZE) {
            Node node = QUEUE.poll();
            if (node == null) {
                throw new RuntimeException("data error");
            }

            Object key = node.key;
            remove(key);
        }
    }

    private void sizeDown() {
        if (QUEUE.size() == 0) {
            flag = false;
        }
        this.size.decrementAndGet();
    }

    @Override
    public int size() {
        return size.get();
    }

    /**
     * 链表
     */
    private class Node {
        private Node next;
        private Node pre;
        private Object key;
        private Object value;
        private Long updateTime;

        public Node(Node next, Node pre, Object key, Object value) {
            this.next = next;
            this.pre = pre;
            this.key = key;
            this.value = value;
            this.updateTime = System.currentTimeMillis();
        }

        public Long getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Long updateTime) {
            this.updateTime = updateTime;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public int hash(Object key) {
        int h;
        return key == null ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private class CheckTimeThread implements Runnable {

        @Override
        public void run() {
            while (flag) {
                Node node = QUEUE.poll();
                if (node == null) {
                    continue;
                }
                Long updateTime = node.updateTime;
                if ((System.currentTimeMillis() - updateTime) > EXPIRE_TIME) {
                    remove(node.key);
                }
            }
        }
    }
}
