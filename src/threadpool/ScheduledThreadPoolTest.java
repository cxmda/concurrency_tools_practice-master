package threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author chen
 * @create 2020-06-14 16:36
 */
public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        //scheduledExecutorService.schedule(new Task(),3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
    }
}
