package domain;

public class Table {
    private final int number;

    public Table(final int number) {
        this.number = number;
    }

    public final int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }
}
