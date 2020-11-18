package CI646.threads0;

public class Interleaved {

    public void show() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " - Number: " + i);
            Utils.simulateInterrupt(10);
        }
    }

}
