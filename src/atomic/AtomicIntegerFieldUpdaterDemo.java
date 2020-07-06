package atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 演示AtomicIntegerFieldUpdater的用法
 * @author chen
 * @create 2020-07-06 21:07
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable{

    static Candidate tom;
    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> atomicIntegerFieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tom.score++;
            atomicIntegerFieldUpdater.getAndIncrement(peter);
        }
    }

    public static class Candidate{
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        tom = new Candidate();
        peter = new Candidate();
        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(tom.score);
        System.out.println(peter.score);
    }
}
