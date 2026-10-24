
 // Lab-6: Write a Java program to create a thread class.

class MyThread extends Thread {

    MyThread(String name) {
        super(name);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(getName() + " is running: " + i);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
                return;
            }
        }
    }
}

public class MyThreads {
    public static void main(String[] args) {

        MyThread thread1 = new MyThread("Thread-A");
        MyThread thread2 = new MyThread("Thread-B");

        thread1.start();
        thread2.start();
    }
}
