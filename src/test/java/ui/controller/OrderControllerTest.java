package ui.controller;

import domain.model.Order;
import domain.model.Table;
import domain.repository.OrderRepository;
import domain.repository.TableRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import ui.controller.exception.*;
import ui.view.MainView;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class OrderControllerTest {
    @BeforeAll
    static void mock() {
        MockedStatic<MainView> mainViewMockedStatic = mockStatic(MainView.class);
    }

    @Test
    void check_invalid_FunctionNumber() {
        assertThrows(NoFunctionException.class, () -> OrderController.checkFunctionNumber(0));
    }

    @Test
    void check_invalid_TableNumber() {
        assertThrows(NoTableException.class, () -> OrderController.checkTableNumber(0));
    }

    @Test
    void check_invalid_MenuNumber() {
        assertThrows(NoMenuException.class, () -> OrderController.checkMenuNumber(0));
    }

    @Test
    void check_limitOver_Quantity() {
        assertThrows(QuantityLimitOverException.class, () -> OrderController.checkQuantity(100));
    }

    @Test
    void check_nonOrdered_Table() {
        assertThrows(NoOrderException.class, () -> OrderController.checkOrderedTable(8));
    }

    @Test
    void check_invalid_PaymentType() {
        assertThrows(NoPaymentTypeException.class, () -> OrderController.checkPaymentType(0));
    }

    @Test
    void register() {
        int tableNumber = 1;
        Table table = TableRepository.findByNumber(tableNumber);

        OrderController.register(tableNumber, 1, 1);
        OrderController.register(tableNumber, 2, 2);

        List<Order> orders = OrderRepository.query(table);
        assertEquals(2, orders.size());
    }

    @Test
    void completePay() {
        int tableNumber = 1;
        OrderController.register(tableNumber, 1, 1);

        OrderController.completePay(tableNumber);

        assertTrue(OrderRepository.isEmpty(TableRepository.findByNumber(tableNumber)));
    }

    @Test
    void pay() {
    }
}