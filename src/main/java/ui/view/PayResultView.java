package ui.view;

import domain.model.Order;
import ui.controller.OrderController;

import java.util.List;

public class PayResultView {
    public static void exec(int tableNumber, List<Order> orders, int payment) {
        printPayStart(tableNumber);
        printOrders(orders);
        printPayment(payment);
        OrderController.completePay(tableNumber);
    }

    public static void printPayStart(final int tableNumber) {
        System.out.println("## " + tableNumber + "번 테이블의 결제를 진행합니다.");
        System.out.println();
    }

    public static void printOrders(final List<Order> orders) {
        System.out.println("## 주문 내역");
        System.out.println("메뉴  수량  금액");
        for (Order order : orders) {
            System.out.println(order);
        }
        System.out.println();
    }

    public static void printPayment(int payment) {
        System.out.println("## 최종 결제 할 금액");
        System.out.println(payment);
        System.out.println();
    }
}
