package Thread;

public class ThreadMain {
    public static void main(String[] args) {
        MyRun run = new MyRun();
        new Thread(run).start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main: " + i);
        }
    }
}

class MyThread extends Thread {
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("t: " +i);
        }
    }
}

class MyRun implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("t: " +i);
        }
    }
}
