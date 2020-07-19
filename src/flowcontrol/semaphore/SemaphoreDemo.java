package flowcontrol.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 演示Semaphore用法
 * @author chen
 * @create 2020-07-19 21:18
 */
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(5,true);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new SemaphoreDemo.Task());
        }
        executorService.shutdown();
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            try {
                semaphore.acquire(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "拿到了许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release(3);
        }
    }
}
