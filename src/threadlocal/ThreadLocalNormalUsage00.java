package threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 两个线程打印日期
 * @author chen
 * @create 2020-06-15 21:15
 */
@SuppressWarnings("all")
public class ThreadLocalNormalUsage00 {

    public static void main(String[] args) {
        new Thread(() ->{
            String date = new ThreadLocalNormalUsage00().date(10);
            System.out.println(date);
        }).start();
        new Thread(() ->{
            String date = new ThreadLocalNormalUsage00().date(1002);
            System.out.println(date);
        }).start();
    }

    public String date(int seconds){
        //参数的单位是毫秒 从1970-01-01 00:00:00 GMT开始计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }
}
