package threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 演示关闭线程池
 *
 * @author chen
 * @create 2020-06-14 17:11
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(new ShutDownThead());
        }
        Thread.sleep(1500);
//        System.out.println(executorService.isShutdown());
//        executorService.shutdown();
//        System.out.println(executorService.isShutdown());
//        System.out.println(executorService.isTerminated());
//        Thread.sleep(10000);
//        System.out.println(executorService.isTerminated());
        List<Runnable> runnables = executorService.shutdownNow();
        executorService.execute(new ShutDownThead());
    }
}

class ShutDownThead implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "线程中断了");
        }
    }
}
