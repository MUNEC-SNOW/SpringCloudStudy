package Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReenterLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();
    }
//    public static void main(String[] args) {
//        Phone phone = new Phone();
//        new Thread(() -> {
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }, "t1").start();
//        new Thread(()->{
//            try {
//                phone.sendSms();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        },"t2").start();
//    }
}
//
//class Phone {
//    public synchronized void sendSms() throws Exception {
//        System.out.println(Thread.currentThread().getName() + "\t sendSms");
//        sendEmail();
//    }
//
//    public synchronized void sendEmail() throws Exception {
//        System.out.println(Thread.currentThread().getName() + "\t sendEmail");
//    }
//}

class Phone implements Runnable {
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tget");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\tset");
        } finally {
            lock.unlock();
        }
    }
}
