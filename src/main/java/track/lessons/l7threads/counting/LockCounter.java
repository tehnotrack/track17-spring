package track.lessons.l7threads.counting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * реализация счетчика через лок
 */
public class LockCounter implements Counter {

    private long counter;

    Lock lock = new ReentrantLock();

    public long inc2() {
        lock.lock();
        try {
            return counter++;
        } finally {
            lock.unlock();
        }
    }

    public synchronized long inc() {
        return counter++;
    }

    public long incUnsafe() {
        return counter++;
    }


}
