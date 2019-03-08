package me.moon.algo;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LRui
 * @date 2019-1-16 11:35
 */
public class HappyNumber {

    static boolean isHappyNumber(int number) {
        Set<Integer> set = new HashSet<>();
        while (number != 1) {
            int tmp = 0;
            while (number > 0) {
                tmp += (number % 10) * (number % 10);
                number /= 10;
            }
            if (set.contains(tmp)) {
                return false;
            }
            set.add(tmp);
            number = tmp;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("19 " + isHappyNumber(19));
        System.out.println("11 " + isHappyNumber(11));
        System.out.println("100 " + isHappyNumber(100));
    }
}
