package domain.model;

import ui.controller.exception.NoFunctionException;

public enum FunctionNumber {
    REGISTER(1),
    PAY(2),
    EXIT(3);

    private final int value;

    FunctionNumber(final int value) {
        this.value = value;
    }

    public static FunctionNumber findByValue(final int value) {
        for (FunctionNumber functionNumber : FunctionNumber.values()) {
            if (functionNumber.getValue() == value) {
                return functionNumber;
            }
        }
        throw new NoFunctionException();
    }

    public boolean isRegister() {
        return value == FunctionNumber.REGISTER.value;
    }

    public boolean isPay() {
        return value == FunctionNumber.PAY.value;
    }

    public boolean isExit() {
        return value == FunctionNumber.EXIT.value;
    }

    private int getValue() {
        return value;
    }
}
