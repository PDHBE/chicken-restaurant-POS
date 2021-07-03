package application;

import domain.model.Quantity;
import domain.repository.MenuRepository;
import domain.repository.OrderRepository;
import domain.repository.TableRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderServiceTest {
    @Test
    void register() {
        OrderService.register(TableRepository.findByNumber(1),
                MenuRepository.findByNumber(2),
                new Quantity(2));
        OrderService.register(TableRepository.findByNumber(1),
                MenuRepository.findByNumber(3),
                new Quantity(4));
        OrderService.register(TableRepository.findByNumber(1),
                MenuRepository.findByNumber(21),
                new Quantity(3));

        assertEquals(3, OrderRepository.query(TableRepository.findByNumber(1)).size());
    }

    @Test
    void pay() {
        //PayCalculationServiceTest 로 대체
    }
}