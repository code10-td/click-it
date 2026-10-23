
 // Lab-7: Illustrate yield(), stop() and sleep() methods using thread.

class MyThread extends Thread {

    private volatile boolean running = true;

    public void stopThread() {
        running = false;
    }

    public void run() {
        for (int i = 1; i <= 10 && running; i++) {
            System.out.println(getName() + ": " + i);

            Thread.yield();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
                return;
            }
        }
    }
}

public class ThreadMethods {

    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.setName("Thread-A");
        t2.setName("Thread-B");

        t1.start();
        t2.start();

        Thread.sleep(1500);

        t1.stopThread();

        System.out.println("Thread-A stopped.");
    }
}
