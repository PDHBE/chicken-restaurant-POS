package domain.model;

import ui.controller.exception.QuantityLimitOverException;

public class Quantity {
    private static final int LIMIT = 99;
    private final int value;

    public Quantity(int value){
        if(value > LIMIT){
            throw new QuantityLimitOverException();
        }
        this.value = value;
    }

    public final int getValue(){
        return value;
    }
}
