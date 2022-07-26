import java.util.concurrent.TimeUnit;

public class JucMain {
    public static void main(String[] args) {
//        Thread.currentThread().getThreadGroup().list();
//        System.out.println("------------>");
//        System.out.println(Thread.activeCount());
        /* output
        java.lang.ThreadGroup[name=main,maxpri=10]
        Thread[main,5,main]
        Thread[Monitor Ctrl-Break,5,main]
        ------------>
        2*/
        System.out.println("output1");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("output2");
    }
}
