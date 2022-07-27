package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReThreadDemo {
    static AtomicReference<Thread> atomicReference = new AtomicReference<>();
    static Thread thread;

    public static void lock() {
        thread=Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t"+"coming.....");
        while(!atomicReference.compareAndSet(null,thread)){

        }
    }

    public static void unlock(){
        System.out.println(Thread.currentThread().getName()+"\t"+"over.....");
        atomicReference.compareAndSet(thread,null);
    }
    public static void main(String[] args) {
        new Thread(()->{
            AtomicReThreadDemo.lock();
            try { TimeUnit.SECONDS.sleep(3);  } catch (InterruptedException e) {e.printStackTrace();}
            AtomicReThreadDemo.unlock();
        },"A").start();

        new Thread(()->{
            AtomicReThreadDemo.lock();
            AtomicReThreadDemo.unlock();
        },"B").start();
    }

}
