package me.moon.algo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LRui
 * @date 2019-1-16 14:25
 */
public class TwoSum {

    public static int[] getTwo(int[] nums, int target) {
        int[] result = new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                result = new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 7};
        int[] two1 = getTwo(nums, 10);
        int[] two2 = getTwo(nums, 11);
        int[] two3 = getTwo(nums, 12);
        System.out.println(Arrays.toString(two1));
        System.out.println(Arrays.toString(two2));
        System.out.println(Arrays.toString(two3));
    }
}
