package track.lessons.l7threads;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class Sync {


    Lock lock = new ReentrantLock();

    private int val = 0;

    void foo() {
        try {
            lock.lock();

            val++;
        } finally {
            lock.unlock();
        }

    }

    private int val2 = 0;

    synchronized int getVal() {
        return val2 + val;
    }

    synchronized void setVal(int val) {
        val2 = val;
    }



    public String name;



    public static void main(String[] args) {
        Sync sync = new Sync();

    }
}
