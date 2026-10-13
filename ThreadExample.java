//lab-6:Write a Java program to create thread class. 
class MyThread extends Thread {

    public void run() {
        System.out.println("Thread is running");
    }
}

class ThreadExample {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();
    }
}
