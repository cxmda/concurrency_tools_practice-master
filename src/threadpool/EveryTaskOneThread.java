package threadpool;

/**
 * @author chen
 * @create 2020-06-09 22:24
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("执行了任务");
        }
    }
}
