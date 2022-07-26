package Thread;

import java.util.concurrent.ExecutionException;

public class ThreadControl {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("im child Thread");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
//        t1.setDaemon(true);
        t1.start();
        Thread.sleep(3000);
        System.out.println("mian done");
    }
}
