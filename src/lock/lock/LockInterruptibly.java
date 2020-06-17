package lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chen
 * @create 2020-06-17 22:22
 */
public class LockInterruptibly implements Runnable{

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly r = new LockInterruptibly();
        Thread thread0 = new Thread(r);
        Thread thread1 = new Thread(r);
        thread0.start();
        thread1.start();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁");
        try {
            //尝试去获取锁，会一直等待，期间可以被中断
            lock.lockInterruptibly();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到了锁");
                Thread.sleep(3000);
            }catch (InterruptedException e){
                System.out.println(Thread.currentThread().getName() + "睡眠期间被中断了");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "尝试获取锁期间被中断了");
        }
    }
}
