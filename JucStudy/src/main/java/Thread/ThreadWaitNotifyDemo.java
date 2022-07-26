package Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadWaitNotifyDemo {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(() -> { for (int i=1; i<11; i++) airCondition.increment();}, "A").start();
        new Thread(() -> { for (int i=1; i<11; i++) airCondition.decrement();}, "B").start();
        new Thread(() -> { for (int i=1; i<11; i++) airCondition.increment();}, "C").start();
        new Thread(() -> { for (int i=1; i<11; i++) airCondition.decrement();}, "D").start();
    }
}

//class AirCondition {
//    private int number = 0;
//    public synchronized void increment(){
//        while (number != 0) {
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        number++;
//        System.out.println(Thread.currentThread().getName()+":"+number);
//        this.notify();
//    }
//
//    public synchronized void decrement() {
//        while(number == 0) {
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        number--;
//        System.out.println(Thread.currentThread().getName()+":"+number);
//        this.notify();
//    }
//}

class AirCondition {
    private int number = 0;

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try{
            while(number!=0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+":\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while(number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+":\t"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
