package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1000个打印日期的任务，用线程池完成
 * @author chen
 * @create 2020-06-15 21:15
 */
@SuppressWarnings("all")
public class ThreadLocalNormalUsage04 {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(() ->{
                String date = new ThreadLocalNormalUsage04().date(finalI);
                System.out.println(date);
            });
        }
        executorService.shutdown();
    }

    public String date(int seconds){
        //参数的单位是毫秒 从1970-01-01 00:00:00 GMT开始计时
        Date date = new Date(1000 * seconds);
        String s;
        //使用synchronized能保证线程安全，但是会影响性能
        synchronized (ThreadLocalNormalUsage04.class){
            s = simpleDateFormat.format(date);
        }
        return s;
    }
}
