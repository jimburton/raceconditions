package CI346.threads0;

public class Interleaved {

    public void show() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " - Number: " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()
                        + " was interrupted while sleeping");
            }
        }
    }

}
