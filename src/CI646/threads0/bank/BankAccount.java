package CI646.threads0.bank;

public class BankAccount {
    public static BankAccount account;
    private static int balance = 1000;
    private static Person person;

    private BankAccount() {
    }

    public static BankAccount getAccount(Person p) {
        if (account == null) {
            account = new BankAccount();
        }
        BankAccount.person = p;
        return account;
    }

    public static int getBal() {
        return balance;
    }

    public synchronized void withdraw(int bal) {
        try {

            if (balance >= bal) {
                System.out.println(person.getName() + " " + "is trying to withdraw");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                balance = balance - bal;
                System.out.println(person.getName() + " " + "is completing the withdraw");
            } else {
                System.out.println(person.getName() + " " + "doesn't have enough money for the withdrawal ");
            }
            System.out.println(person.getName() + " " + " withdraw £" + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized void deposit(int bal) {
        try {
            if (bal > 0) {
                System.out.println(person.getName() + " " + " is trying to deposit");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                balance = balance + bal;
                System.out.println(person.getName() + " " + "is completing the deposit");
            } else {
                System.out.println(person.getName() + " " + "doesn't have enough money for deposit");
            }
            System.out.println(person.getName() + " " + " deposit £" + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
