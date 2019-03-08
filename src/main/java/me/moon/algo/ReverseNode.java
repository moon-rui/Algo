package me.moon.algo;

import java.util.Stack;

/**
 * @author LRui
 * @date 2019-1-16 14:47
 */
public class ReverseNode {

    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    static void stackMethod(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        while (!stack.empty()) {
            System.out.print(stack.pop().data + "  ");
        }
    }

    static void headInsertion(Node node) {
        Node pre = node;
        Node cur = node.next;
        pre.next = null;
        Node next;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        while (pre != null) {
            System.out.print(pre.data + "  ");
            pre = pre.next;
        }
    }

    static void recursiveMethod(Node node) {
        if (node == null) {
            return;
        }
        recursiveMethod(node.next);
        System.out.print(node.data + "  ");
    }

    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(3);
        Node<Integer> n4 = new Node<>(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        stackMethod(n1);
        System.out.println();
        recursiveMethod(n1);
        System.out.println();
        headInsertion(n1);

    }
}
