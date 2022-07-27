package Thread;

public class FakeAwake {
    public static void main(String[] args) {
        Noodles noodles = new Noodles();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    noodles.makeNoodles();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"chiefA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    noodles.makeNoodles();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"chiefB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    noodles.eat();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"ConA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    noodles.eat();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"ConB").start();
    }

}

class Noodles {
    private int num = 0;

    public synchronized void makeNoodles() throws Exception {
        while (num != 0) {
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"做好了一份面，当前有"+num+"份面");
        this.notifyAll();
    }

    public synchronized void eat() throws Exception {
        while (num == 0) {
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"吃了一份面，当前有"+num+"份面");
        this.notify();
    }
}
