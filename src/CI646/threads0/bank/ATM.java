package CI646.threads0.bank;

public class ATM extends Thread implements Runnable {

    private Person person;

    public static enum PEOPLE {
        P1 ("person 1"),
        P2 ("person 2"),
        P3 ("person 3");
        private static PEOPLE[] vals = values();

        private final String name;

        PEOPLE(String s) {
            this.name = s;
        }

        public PEOPLE next(){
            return vals[(this.ordinal()+1) % vals.length];
        }
    }

    public ATM(Person p) {
        this.person = p;
    }

    public static void main(String[] args) {

        ATM ts1 = new ATM(new Person(PEOPLE.P1));
        ts1.start();

        ATM ts2 = new ATM(new Person(PEOPLE.P2));
        ts2.start();

        ATM ts3 = new ATM(new Person(PEOPLE.P3));
        ts3.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                BankAccount acc = BankAccount.getAccount(person);
                acc.withdraw(100);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ex) {
                    System.out.println(ATM.class.getName()+" interrupted!");
                }
                if (acc.getBal() < 0) {
                    System.out.println("account is overdrawn!");
                }
                acc.deposit(200);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Final Acc balance is £" + BankAccount.getBal());
    }
}
