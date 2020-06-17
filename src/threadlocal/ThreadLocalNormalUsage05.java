package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 利用ThreadLocal，给每个线程分配自己的dataFormat对象，保证了线程安全，高效利用内存
 *
 * @author chen
 * @create 2020-06-15 21:15
 */
@SuppressWarnings("all")
public class ThreadLocalNormalUsage05 {

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                String date = new ThreadLocalNormalUsage05().date(finalI);
                System.out.println(date);
            });
        }
        executorService.shutdown();
    }

    public String date(int seconds) {
        //参数的单位是毫秒 从1970-01-01 00:00:00 GMT开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = ThreadSafeFormatter.dateFormatThreadLocal2.get();
        return simpleDateFormat.format(date);
    }
}

class ThreadSafeFormatter {
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal2 =
            ThreadLocal.withInitial(() ->
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
