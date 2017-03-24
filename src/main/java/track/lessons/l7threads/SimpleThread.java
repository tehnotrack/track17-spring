package track.lessons.l7threads;

import java.util.concurrent.TimeUnit;

/**
 * Операции start/sleep/join
 */
public class SimpleThread {

    public static void main(String[] args) throws Exception {
//        inParallel();
        join();

//        System.out.println(TimeUnit.DAYS.toMillis(1));

//        System.out.println(Thread.currentThread().getName());
//        MyThread thread = new MyThread(10);
//        thread.start(); // magic!
//
//        thread.isAlive();
//        thread = new MyThread(10);
//        thread.start();

    }

    private static void inParallel() {

        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("MyThread " + " : " + i);
                    mysleep(2);
                }
            }
        };
        // Запуск кода в новом треде
        System.out.println("Starting thread");
        t1.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main:" + i);
            mysleep(1);
        }
        System.out.println("Main thread finished");
    }


    static void join() throws Exception {
        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("MyThread " + " : " + i);
                    mysleep(1);
                }
            }
        };
        System.out.println("Starting thread...");
        thread.start();
        System.out.println("Joining");
        thread.join();
        System.out.println("Main exit");


    }

    private static void mysleep(int sec) {
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (InterruptedException e) {
            // ignored
        }
    }


    static class MyThread extends Thread {
        private int val;

        public MyThread(int val) {
            System.out.println(getName());
            this.val = val;
        }

        @Override
        public void run() {
            System.out.println(val);
        }
    }

}
