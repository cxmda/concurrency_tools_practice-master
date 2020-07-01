package lock.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author chen
 * @create 2020-06-29 22:29
 */
public class Upgrading {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    private static void readUpgrading(){
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取到读锁，正在读取");
            Thread.sleep(1000);
            System.out.println("升级会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取到了写锁，升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }

    private static void writeDowngrading(){
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "获取到写锁，正在读取");
            Thread.sleep(1000);
            readLock.lock();
            System.out.println("在不释放写锁的情况下，直接获取读锁，成功降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先演示锁是可以降级的");
        Thread thread1 = new Thread(() -> writeDowngrading());
        thread1.start();
        thread1.join();
        System.out.println("---------------");

        System.out.println("演示锁升级是不可以的");
        Thread thread2 = new Thread(() -> readUpgrading());
        thread2.start();
    }
}
