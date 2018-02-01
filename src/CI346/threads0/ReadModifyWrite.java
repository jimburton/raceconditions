package CI346.threads0;

// Example adapted from https://www.javacodegeeks.com

public class ReadModifyWrite {
    private int number;

    public void incrementNumber() {
        int n = number;
        try {
            Thread.sleep(1);//simulate interruption
        } catch (InterruptedException e) {}
        number = n+1;
    }

    public int getNumber() {
        return this.number;
    }

    public static void main(String[] args) throws InterruptedException {
        final ReadModifyWrite rmw = new ReadModifyWrite();

        for (int i = 0; i < 100; i++) {
            new Thread(() -> rmw.incrementNumber()).start();
        }

        Thread.sleep(5000);
        System.out.println("Final number (should be 100): " + rmw.getNumber());
    }
}
