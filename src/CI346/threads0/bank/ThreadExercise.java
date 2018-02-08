package CI346.threads0.bank;

public class ThreadExercise extends Thread implements Runnable {

    private Person person;

    public ThreadExercise(Person p) {
        this.person = p;
    }

    public static void main(String[] args) {

        ThreadExercise ts1 = new ThreadExercise(new Person("person 1"));
        ts1.start();
        ThreadExercise ts2 = new ThreadExercise(new Person("person 2"));
        ts2.start();
        ThreadExercise ts3 = new ThreadExercise(new Person("person 3"));
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
                    System.out.println(ThreadExercise.class.getName()+" interrupted!");
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
