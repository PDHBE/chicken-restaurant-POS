package application;

import domain.model.*;
import domain.repository.OrderRepository;
import domain.service.PayCalculationService;

import java.util.List;

public class OrderService {
    public static void register(Table table, Menu menu, Quantity quantity) {
        OrderRepository.register(new Order(table, menu, quantity));
    }

    public static int pay(Table table, PaymentType paymentType) {
        List<Order> orders = OrderRepository.query(table);
        return PayCalculationService.calculate(orders, paymentType);
    }
}
