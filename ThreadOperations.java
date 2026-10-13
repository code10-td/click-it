//lab-5:Write a Java program to illustrate suspend, resume and stop operation of thread. 
class MyThread extends Thread {

    boolean suspended = false;
    boolean stopped = false;

    public synchronized void suspendThread() {
        suspended = true;
    }

    public synchronized void resumeThread() {
        suspended = false;
        notify();
    }

    public synchronized void stopThread() {
        stopped = true;
        suspended = false;
        notify();
    }

    public void run() {
        int i = 1;

        try {
            while (true) {
                synchronized (this) {
                    if (stopped)
                        break;

                    while (suspended && !stopped)
                        wait();

                    if (stopped)
                        break;
                }

                System.out.println("Thread running: " + i++);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Thread stopped.");
    }
}

public class ThreadOperations{
    public static void main(String[] args)
            throws InterruptedException {

        MyThread t = new MyThread();
        t.start();

        Thread.sleep(2000);
        t.suspendThread();
        System.out.println("Thread suspended.");

        Thread.sleep(2000);
        t.resumeThread();
        System.out.println("Thread resumed.");

        Thread.sleep(2000);
        t.stopThread();
    }
}
