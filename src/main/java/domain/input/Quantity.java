package domain.input;

public class Quantity {
    private static final int LIMIT = 99;
    private final int value;

    public Quantity(int value) throws IllegalArgumentException{
        if(value > LIMIT){
            throw new IllegalArgumentException("수량은 최대 " + LIMIT + "개 까지 가능합니다.");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
