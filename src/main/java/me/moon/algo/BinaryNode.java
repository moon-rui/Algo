package me.moon.algo;

import java.util.LinkedList;

/**
 * @author LRui
 * @date 2019-1-16 10:29
 */
public class BinaryNode {

    private Object data;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode(Object data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

    public void levelIterator() {
        LinkedList<BinaryNode> queue = new LinkedList<>();
        queue.offer(this);

        while (!queue.isEmpty()) {
            BinaryNode current = queue.poll();
            System.out.print(current.data + "   ");

            if (current.getLeft() != null) {
                queue.offer(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.offer(current.getRight());
            }
        }
    }

    public static void main(String[] args) {
        BinaryNode n1 = new BinaryNode("1", null, null);
        BinaryNode n2 = new BinaryNode("2", null, null);
        BinaryNode n3 = new BinaryNode("3", null, null);
        BinaryNode n4 = new BinaryNode("4", null, null);
        BinaryNode n5 = new BinaryNode("5", null, null);
        BinaryNode n6 = new BinaryNode("6", null, null);
        n1.setLeft(n2);
        n1.setRight(n3);
        n2.setLeft(n4);
        n3.setLeft(n5);
        n3.setRight(n6);

        n1.levelIterator();
    }
}
