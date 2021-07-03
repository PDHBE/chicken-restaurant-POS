package ui.controller;

import application.OrderService;
import domain.model.*;
import domain.repository.MenuRepository;
import domain.repository.OrderRepository;
import domain.repository.TableRepository;
import ui.view.MainView;
import ui.view.PayResultView;
import ui.view.PayView;
import ui.view.RegisterView;

import java.util.ArrayList;
import java.util.List;

public class OrderController {
    public static void execFunction(int inputtedFunctionNumber) {
        FunctionNumber functionNumber = FunctionNumber.findByValue(inputtedFunctionNumber);
        if (functionNumber.isRegister()) {
            RegisterView.exec(getAllTables(), getOrderedTables(), getMenus());
            return;
        }
        if (functionNumber.isPay()) {
            PayView.exec(getAllTables(), getOrderedTables());
            return;
        }
        if (functionNumber.isExit()) {
            return;
        }
    }

    public static void register(int tableNumber, int menuNumber, int quantityValue) {
        Table table = TableRepository.findByNumber(tableNumber);
        Menu menu = MenuRepository.findByNumber(menuNumber);
        Quantity quantity = new Quantity(quantityValue);
        OrderService.register(table, menu, quantity);
        MainView.exec();
    }

    public static void pay(int tableNumber, int paymentTypeValue) {
        Table table = TableRepository.findByNumber(tableNumber);
        PaymentType paymentType = PaymentType.findByValue(paymentTypeValue);
        int payment = OrderService.pay(table, paymentType);
        PayResultView.exec(tableNumber, getOrders(tableNumber), payment);
    }

    public static void completePay(int tableNumber) {
        OrderRepository.clear(TableRepository.findByNumber(tableNumber));
        MainView.exec();
    }

    public static void checkFunctionNumber(int functionNumber) {
        FunctionNumber.findByValue(functionNumber);
    }

    public static void checkTableNumber(int tableNumber) {
        TableRepository.findByNumber(tableNumber);
    }

    public static void checkMenuNumber(int inputtedMenuNumber) {
        MenuRepository.findByNumber(inputtedMenuNumber);
    }

    public static void checkQuantity(int inputtedQuantity) {
        new Quantity(inputtedQuantity);
    }

    public static void checkOrderedTable(int inputtedTableNumber) {
        Table table = TableRepository.findByNumber(inputtedTableNumber);
        OrderRepository.query(table);
    }

    public static void checkPaymentType(int inputtedPaymentType) {
        PaymentType.findByValue(inputtedPaymentType);
    }

    private static List<Table> getAllTables() {
        return TableRepository.tables();
    }

    private static List<Table> getOrderedTables() {
        List<Table> orderedTables = new ArrayList<>();
        for (Table table : getAllTables()) {
            if (!OrderRepository.isEmpty(table)) {
                orderedTables.add(table);
            }
        }
        return orderedTables;
    }

    private static List<Menu> getMenus() {
        return MenuRepository.menus();
    }

    private static List<Order> getOrders(int tableNumber) {
        return OrderRepository.query(TableRepository.findByNumber(tableNumber));
    }
}
