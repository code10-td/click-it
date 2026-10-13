//lab-7: Write a Java program to illustrate yield(), stop() and sleep() method using thread. 
class MyThread extends Thread {

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + ": " + i);

            Thread.yield();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted");
                return;
            }
        }
    }
}

public class ThreadMethods {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread 1");
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}
