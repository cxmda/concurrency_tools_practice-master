package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock锁不会像synchronized一样，异常的时候自动释放锁，所以最佳实践是，
 * finally中释放锁，以便保证发生异常的时候锁一定被释放
 * @author chen
 * @create 2020-06-17 21:31
 */
public class MustUnLock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始执行任务");
        }finally {
            lock.unlock();
        }
    }
}
