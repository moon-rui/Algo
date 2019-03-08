package me.moon.algo.odd_even;

/**
 * @author LRui
 * @date 2019-1-16 16:37
 */
public class NotifyAndWaitWay {

    private int count = 1;
    private boolean flag = false;

    static class OddNum implements Runnable {

        private NotifyAndWaitWay way;

        public OddNum(NotifyAndWaitWay way) {
            this.way = way;
        }

        @Override
        public void run() {
            while (way.count <= 100) {
                synchronized (NotifyAndWaitWay.class) {
                    if (!way.flag) {
                        System.out.println("odd  " + way.count++);
                        way.flag = true;
                        NotifyAndWaitWay.class.notify();
                    } else {
                        try {
                            NotifyAndWaitWay.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    static class EvenNum implements Runnable {

        private NotifyAndWaitWay way;

        public EvenNum(NotifyAndWaitWay way) {
            this.way = way;
        }

        @Override
        public void run() {
            while (way.count <= 100) {
                synchronized (NotifyAndWaitWay.class) {
                    if (way.flag) {
                        System.out.println("even " + way.count++);
                        way.flag = false;
                        NotifyAndWaitWay.class.notify();
                    } else {
                        try {
                            NotifyAndWaitWay.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NotifyAndWaitWay way = new NotifyAndWaitWay();
        Thread t1 = new Thread(new OddNum(way));
        Thread t2 = new Thread(new EvenNum(way));
        t1.start();
        t2.start();
    }
}
