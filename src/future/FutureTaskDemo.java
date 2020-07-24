package future;

import java.util.concurrent.*;

/**
 * 演示FutureTask的用法
 * @author chen
 * @create 2020-07-24 21:01
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(futureTask);
        try {
            System.out.println("task运行结果：" + futureTask.get());
            executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}

class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}
