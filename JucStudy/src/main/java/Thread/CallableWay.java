package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableWay {
    public static void main(String[] args) {
        NumThread m = new NumThread();
        FutureTask futureTask = new FutureTask(m);
        new Thread(futureTask, "1").start();
        new Thread(futureTask, "2").start();
        try {
            Object sum = futureTask.get();
            System.out.println("sum: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class NumThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("call has been called;");
        // only call once;
        return 6;
    }
}
