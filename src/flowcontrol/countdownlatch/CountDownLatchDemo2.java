package flowcontrol.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟100米跑步，5名选手都准备好了，只等裁判员一声令下，所有人同时开始跑步。
 *
 * @author chen
 * @create 2020-07-19 16:23
 */
public class CountDownLatchDemo2 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            int no = i + 1;
            Runnable runnable = () -> {
                System.out.println("No." + no + "准备好了");
                try {
                    start.await();
                    System.out.println("No." + no + "开始跑步了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.submit(runnable);
        }
        Thread.sleep(5000);
        System.out.println("发令枪响，比赛开始");
        start.countDown();
    }
}
