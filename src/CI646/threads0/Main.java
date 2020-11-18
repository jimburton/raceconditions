package CI646.threads0;

public class Main {

    public static void main(String[] args) {
        Interleaved i = new Interleaved();

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                i.show();
            }
        };
        Thread t1 = new Thread(runner, "Thread 1");
        Thread t2 = new Thread(runner, "Thread 2");
        Thread t3 = new Thread(runner, "Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
