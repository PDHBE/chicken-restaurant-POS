package domain.service;

import domain.model.*;
import domain.repository.MenuRepository;
import domain.repository.TableRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayCalculationServiceTest {
    private static final int CHICKEN_DISCOUNT_CRITERIA = 10;
    private static final int CHICKEN_DISCOUNT_AMOUNT = 10000;
    private static final double CASH_DISCOUNT_RATE = 0.05;
    private static final Table table = TableRepository.findByNumber(1);
    private static final Menu chickenMenu = MenuRepository.findByNumber(1);
    private static final Menu nonChickenMenu = MenuRepository.findByNumber(21);

    @Test
    void calculate_nonDiscount() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(table, chickenMenu,
                new Quantity(CHICKEN_DISCOUNT_CRITERIA - 1)));
        orders.add(new Order(table, nonChickenMenu,
                new Quantity(3)));
        int rawAmount = 0;
        for (Order order : orders) {
            rawAmount += order.calculateAmount();
        }

        int actual = PayCalculationService.calculate(orders, PaymentType.CARD);

        assertEquals(rawAmount, actual);
    }

    @Test
    void calculate_chickenDiscount() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(table, chickenMenu,
                new Quantity(CHICKEN_DISCOUNT_CRITERIA * 2)));
        orders.add(new Order(table, nonChickenMenu,
                new Quantity(3)));
        int rawAmount = 0;
        for (Order order : orders) {
            rawAmount += order.calculateAmount();
        }
        rawAmount -= 2 * CHICKEN_DISCOUNT_AMOUNT;

        int actual = PayCalculationService.calculate(orders, PaymentType.CARD);

        assertEquals(rawAmount, actual);
    }

    @Test
    void calculate_cashDiscount() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(table, chickenMenu,
                new Quantity(CHICKEN_DISCOUNT_CRITERIA - 1)));
        orders.add(new Order(table, nonChickenMenu,
                new Quantity(3)));
        int rawAmount = 0;
        for (Order order : orders) {
            rawAmount += order.calculateAmount();
        }
        rawAmount -= rawAmount * CASH_DISCOUNT_RATE;

        int actual = PayCalculationService.calculate(orders, PaymentType.CASH);

        assertEquals(rawAmount, actual);
    }

    @Test
    void calculate_allDiscount() {
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(table, chickenMenu,
                new Quantity(CHICKEN_DISCOUNT_CRITERIA * 2)));
        orders.add(new Order(table, nonChickenMenu,
                new Quantity(3)));
        int rawAmount = 0;
        for (Order order : orders) {
            rawAmount += order.calculateAmount();
        }
        rawAmount -= 2 * CHICKEN_DISCOUNT_AMOUNT;
        rawAmount -= rawAmount * CASH_DISCOUNT_RATE;

        int actual = PayCalculationService.calculate(orders, PaymentType.CASH);

        assertEquals(rawAmount, actual);
    }
}