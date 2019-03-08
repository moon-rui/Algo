package me.moon.algo;

/**
 * @author LRui
 * @date 2019-1-16 13:44
 */
public class LinkLoop {

    static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static boolean isLoop(Node node) {
        Node slow = node;
        Node fast = node.next;

        while (slow.next != null) {
            Object slowData = slow.data;
            Object fastData = fast.data;

            if (slowData == fastData) {
                return true;
            }

            if (fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;

            if (fast == null) {
                return false;
            }
        }

        return false;
    }
    
}
