//lab-8: Write a Java program to use priority of thread. 
public class ThreadPriority {
    public static void main(String[] args) {

        Thread t1 = new Thread(() ->
            System.out.println("Thread 1 is running | Priority: "
                    + Thread.currentThread().getPriority()));

        Thread t2 = new Thread(() ->
            System.out.println("Thread 2 is running | Priority: "
                    + Thread.currentThread().getPriority()));

        Thread t3 = new Thread(() ->
            System.out.println("Thread 3 is running | Priority: "
                    + Thread.currentThread().getPriority()));

        t1.setPriority(1);
        t2.setPriority(5);
        t3.setPriority(10);

        System.out.println("====================================");
        System.out.println("       JAVA THREAD PRIORITY DEMO");
        System.out.println("====================================");
        System.out.println("Starting all threads...\n");

        t1.start();
        t2.start();
        t3.start();
    }
}
