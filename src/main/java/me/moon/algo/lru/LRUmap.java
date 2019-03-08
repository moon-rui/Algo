package me.moon.algo.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LRui
 * @date 2019-1-21 15:17
 */
public class LRUmap<K, V> {

    private final Map<K, V> cacheMap = new HashMap<>();

    private int cacheSize;

    private int nodeCount;

    private Node<K, V> header;

    private Node<K, V> tailer;

    public LRUmap(int cacheSize) {
        this.cacheSize = cacheSize;

        header = new Node<>();
        tailer = new Node<>();

        header.next = null;
        tailer.pre = null;

        header.pre = tailer;
        tailer.next = header;
    }

    public void put(K key, V value) {
        addNode(key, value);
        cacheMap.put(key, value);
    }

    private void addNode(K key, V value) {
        Node<K, V> node = new Node<>(key, value);

        if (nodeCount == cacheSize) {
            deleteTail();
        }
        addHead(node);
    }

    private void addHead(Node<K, V> node) {
        header.next = node;
        node.pre = header;
        header = node;
        nodeCount++;

        // 如果添加的节点超过两个，把初始化的头尾节点删除
        if (nodeCount == 2) {
            tailer.next.next.pre = null;
            tailer = tailer.next.next;
        }
    }

    private void deleteTail() {
        cacheMap.remove(tailer.key);

        tailer.next.pre = null;
        tailer = tailer.next;

        nodeCount--;
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);

        moveToHead(node);

        return cacheMap.get(key);
    }

    private void moveToHead(Node<K, V> node) {
        if (node.next == null) {
            return;
        }
        if (node.pre == null) {
            node.next.pre = null;
            tailer = node.next;
            nodeCount--;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            nodeCount--;
        }

        // 重新new一个对象，否则node还有保有原来的引用，造成内存溢出
        node = new Node<>(node.getKey(), node.getValue());
        addHead(node);
    }

    private Node<K, V> getNode(K key) {
        Node<K, V> node = tailer;
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V> node = tailer;
        while (node != null) {
            sb.append(node.getKey()).append(":").append(node.getValue()).append(" --> ");
            node = node.next;
        }
        return sb.toString();
    }

    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> pre;
        private Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {

        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
}
