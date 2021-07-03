package domain.repository;

import domain.model.Order;
import domain.model.Table;
import ui.controller.exception.NoOrderException;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private static final List<Order> orders = new ArrayList<>();

    public static void register(Order order) {
        orders.add(order);
    }

    public static List<Order> query(Table table) {
        List<Order> matchedOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.isTable(table)) {
                matchedOrders.add(order);
            }
        }
        if (matchedOrders.isEmpty()) {
            throw new NoOrderException();
        }
        return matchedOrders;
    }

    public static boolean isEmpty(Table table) {
        for (Order order : orders) {
            if (order.isTable(table)) {
                return false;
            }
        }
        return true;
    }

    public static void clear(Table table) {
        orders.removeIf(order -> order.isTable(table));
    }
}
