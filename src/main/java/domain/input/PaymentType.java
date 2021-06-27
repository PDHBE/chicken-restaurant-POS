package domain.input;

import java.util.Arrays;
import java.util.List;

public class PaymentType {
    private static final int CARD = 1;
    private static final int CASH = 2;
    private static final List<Integer> enableValues = Arrays.asList(CARD,CASH);
    private final int value;

    public PaymentType(int value) throws IllegalArgumentException{
        if(!enableValues.contains(value)){
            throw new IllegalArgumentException("존재하지 않는 결제 수단입니다.");
        }
        this.value = value;
    }

    public boolean isCard(){
        return value == CARD;
    }

    public boolean isCash(){
        return value == CASH;
    }
}
