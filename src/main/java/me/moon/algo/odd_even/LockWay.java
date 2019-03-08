package me.moon.algo.odd_even;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LRui
 * @date 2019-1-16 15:54
 */
public class LockWay {

    private int count = 1;
    private volatile boolean flag = false;
    private static Lock lock = new ReentrantLock();

    static class OddNum implements Runnable {

        private LockWay lockWay;

        OddNum(LockWay lockWay) {
            this.lockWay = lockWay;
        }

        @Override
        public void run() {
            while (lockWay.count <= 100) {
                if (!lockWay.flag) {
                    try {
                        lock.lock();
                        System.out.println("odd  " + lockWay.count++);
                        lockWay.flag = true;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }

    static class EvenNum implements Runnable {

        private LockWay lockWay;

        EvenNum(LockWay lockWay) {
            this.lockWay = lockWay;
        }

        @Override
        public void run() {
            while (lockWay.count <= 100) {
                if (lockWay.flag) {
                    try {
                        lock.lock();
                        System.out.println("even " + lockWay.count++);
                        lockWay.flag = false;
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        LockWay lockWay = new LockWay();
        Thread t1 = new Thread(new OddNum(lockWay));
        Thread t2 = new Thread(new EvenNum(lockWay));
        t1.start();
        t2.start();
    }
}
