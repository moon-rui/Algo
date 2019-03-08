package me.moon.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LRui
 * @date 2019-1-15 15:28
 */
public class RedPacket {

    private static final int MAX_PACKET = 200 * 100;
    private static final int MIN_PACKET = 1;

    private static final int NORMAL = 0;
    private static final int MORE = 1;
    private static final int LESS = -1;

    /**
     * the max packet should be average * TIMES, in case of too big packet
     */
    private static final double TIMES = 3.14;

    /**
     * count the recursive in
     */
    private int recursiveCount = 0;

    public List<Integer> splitRedPacket(int sum, int count) {
        List<Integer> packets = new ArrayList<>();

        if (MAX_PACKET * count < sum) {
            System.err.println("超过最大红包限额");
            return packets;
        }

        /**
         * calculate max packet based on TIMES
         */
        int max = (int) ((sum / count) * TIMES);
        max = max > MAX_PACKET ? MAX_PACKET : max;

        for (int i = 0; i < count; i++) {
            int number = randomPacket(sum, MIN_PACKET, max, count - i);
            packets.add(number);

            sum -= number;
        }

        return packets;
    }

    private int randomPacket(int sum, int min, int max, int count) {
        if (count == 1) {
            return sum;
        }

        max = max > sum ? sum : max;

        int packet = (int) (Math.random() * (max - min) + min);

        int remain = sum - packet;
        int status = checkPacket(remain, count - 1);
        switch (status) {
            case MORE:
                recursiveCount++;
                System.out.println("recursive " + recursiveCount);
                return randomPacket(sum, packet, max, count);
            case LESS:
                recursiveCount++;
                System.out.println("recursive " + recursiveCount);
                return randomPacket(sum, min, packet, count);
            default:
                return packet;
        }
    }

    private int checkPacket(int remain, int count) {
        double avg = remain / count;
        if (avg > MAX_PACKET) {
            return MORE;
        }
        if (avg < MIN_PACKET) {
            return LESS;
        }
        return NORMAL;
    }

    public static void main(String[] args) {
        RedPacket redPacket = new RedPacket();
        List<Integer> packets = redPacket.splitRedPacket(5000, 10);
        System.out.println(packets);

        int sum = 0;
        for (Integer packet : packets) {
            sum += packet;
        }
        System.out.println(sum);
    }
}
