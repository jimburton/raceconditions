package CI346.threads0.bank;

import CI346.threads0.Utils;

public class BankAccount {
    public static BankAccount account;
    public static int balance = 1000;
    public static Person person;

    //Singleton constructor
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

    public void withdraw(int bal) {

        if (balance >= bal) {
            System.out.println(person.getName() + " " + "is trying to withdraw");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            balance = balance - bal;
            System.out.println(person.getName() + " " + "is completing the withdrawal");
        } else {
            System.out.println(person.getName() + " " + "doesn't have enough money for the withdrawal ");
        }
        System.out.println(person.getName() + " " + " withdrew £" + balance);
    }

    public void deposit(int bal) {
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
            System.out.println(person.getName() + " " + "doesn't have enough money for the deposit");
        }
        System.out.println(person.getName() + " deposited £" + balance);

    }

    public void transfer(BankAccount to, int bal) {
        if (bal > 0) {
            System.out.println(person.getName() + " " + " is trying to make a transfer");
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            withdraw(bal);
            //artificial interruption
            Utils.simulateInterrupt(100);
            to.deposit(bal);
            System.out.println(person.getName() + " " + "is completing the transfer");
        } else {
            System.out.println(person.getName() + " " + "doesn't have enough money for the deposit");
        }
    }
}
