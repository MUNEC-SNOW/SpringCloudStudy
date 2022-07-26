package Thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask futureTask = new FutureTask(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1024;
        });
        new Thread(futureTask).start();
        while (true) {
            if (futureTask.isDone()) {
                System.out.println("done: " + futureTask.get());
                break;
            } else {
                System.out.println("Blocking......");
            }
        }
    }
}
