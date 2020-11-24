package CI646.threads0;

// Example adapted from https://www.javacodegeeks.com

import java.util.concurrent.atomic.AtomicInteger;

public class CheckThenAct {
    private AtomicInteger number = new AtomicInteger(0);

    public synchronized void changeNumber() {
        number.compareAndSet(0, -1);
        /*
        or...
        number.getAndUpdate(i -> { if (i==0) {
            Utils.simulateInterrupt(5);
            System.out.println(Thread.currentThread().getName() + " | Changed");
            return -1;
        } else {
            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
        return i;
        });
        */
    }

    public static void main(String[] args) {
        final CheckThenAct checkAct = new CheckThenAct();

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    checkAct.changeNumber();
                }
            }, "T" + i).start();
        }
    }
}