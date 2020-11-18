package CI646.threads0.bank;

public class Person {
    private String name;
    private ATM.PEOPLE p;

    public Person (ATM.PEOPLE p) {
        this.p = p;
    }

    public String getName() {
        return p.name();
    }

    public ATM.PEOPLE next() {
        return p.next();
    }

    @Override
    public String toString() {
        return p.name();
    }
}
