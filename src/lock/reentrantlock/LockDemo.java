package lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示ReentrantLock的基本用法，演示被打断
 *
 * @author chen
 * @create 2020-06-20 22:50
 */

public class LockDemo {

    public static void main(String[] args) {
        new LockDemo().init();
    }

    @SuppressWarnings("all")
    private void init() {
        final OutPuter outPuter = new OutPuter();
        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        outPuter.output("孙悟空");
                    }
                }
        ).start();

        new Thread(
                () -> {
                    while (true) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        outPuter.output("大师兄");
                    }
                }
        ).start();
    }

    static class OutPuter {
        Lock lock = new ReentrantLock();

        //字符串打印方法，一个个字符的打印
        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println(" ");
            } finally {
                lock.unlock();
            }
        }
    }
}
