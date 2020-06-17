package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 十个线程打印日期
 * @author chen
 * @create 2020-06-15 21:15
 */
@SuppressWarnings("all")
public class ThreadLocalNormalUsage01 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() ->{
                String date = new ThreadLocalNormalUsage01().date(finalI);
                System.out.println(date);
            }).start();
            Thread.sleep(100);
        }
    }

    public String date(int seconds){
        //参数的单位是毫秒 从1970-01-01 00:00:00 GMT开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
