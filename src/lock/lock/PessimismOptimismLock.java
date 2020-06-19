package lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chen
 * @create 2020-06-19 20:39
 */
public class PessimismOptimismLock {

    int a;

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    public synchronized void testMethod(){
        a++;
    }
}
