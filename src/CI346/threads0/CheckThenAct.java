package CI346.threads0;

// Example adapted from https://www.javacodegeeks.com

public class CheckThenAct {
    private int number;

    public void changeNumber() {
        if (number == 0) {
            try {
                Thread.sleep(5);//simulate an iterruption
            } catch (InterruptedException e) {

            }
            System.out.println(Thread.currentThread().getName() + " | Changed");
            number = -1;
        } else {

            System.out.println(Thread.currentThread().getName() + " | Not changed");
        }
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