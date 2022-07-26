package Thread;

public class SellTicketDemo {
    public static void main(String[] args) {
        SellTicket t = new SellTicket();

        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);

        t1.start();
        t2.start();
        t3.start();
    }
}

class SellTicket implements Runnable {
    private int tickect = 100;

    private Object o = new Object();

    @Override
    public void run() {
        while (true) {
//            synchronized (o) {
            synchronized (this) {
                if (tickect > 0) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"正在出售第"+tickect+"张票");
                    tickect--;
                }
            }
        }
    }
}
