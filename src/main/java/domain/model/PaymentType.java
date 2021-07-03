package domain.model;

import ui.controller.exception.NoPaymentTypeException;

public enum PaymentType {
    CARD(1),
    CASH(2);

    private final int value;

    PaymentType(final int value) {
        this.value = value;
    }

    public static PaymentType findByValue(int value) {
        for (PaymentType paymentType : PaymentType.values()) {
            if (paymentType.getValue() == value) {
                return paymentType;
            }
        }
        throw new NoPaymentTypeException();
    }

    public boolean isCard() {
        return value == PaymentType.CARD.value;
    }

    public boolean isCash() {
        return value == PaymentType.CASH.value;
    }

    private int getValue() {
        return value;
    }
}
