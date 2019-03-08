package me.moon.algo.odd_even;

/**
 * @author LRui
 * @date 2019-1-16 17:01
 */
public class NonBlockingWay implements Runnable {

    private static volatile int flag = 1;

    private int start;
    private int end;
    private String name;

    public NonBlockingWay(int start, int end, String name) {
        this.start = start;
        this.end = end;
        this.name = name;
    }

    @Override
    public void run() {
        while (start <= end) {
            if ((start & 0x01) == flag) {
                System.out.println(name + "  " + start);
                start += 2;
                flag ^= 0x01;
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new NonBlockingWay(1, 100, "odd")).start();
        new Thread(new NonBlockingWay(2, 100, "even")).start();
    }
}
