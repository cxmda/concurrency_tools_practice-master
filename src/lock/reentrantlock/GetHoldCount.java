package lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chen
 * @create 2020-06-21 11:21
 */
public class GetHoldCount {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        //获取拿到锁的次数getHoldCount()
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        System.out.println(lock.getHoldCount());

    }
}
