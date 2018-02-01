package CI346.threads0;

public class Utils {

    public static void simulateInterrupt(long delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()
                    + " was interrupted while sleeping");
        }
    }
}
