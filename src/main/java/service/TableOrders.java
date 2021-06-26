package service;

import domain.Menu;
import domain.Order;
import domain.Table;
import domain.TableRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableOrders {
    private static final int QUANTITY_LIMIT = 99;
    private static final Map<Table, List<Order>> tableOrders = new HashMap<>();

    static {
        for (Table table : TableRepository.tables()) {
            tableOrders.put(table, new ArrayList<>());
        }
    }

    public static void register(Table table, Menu menu, int quantity) throws IllegalArgumentException {
        if (quantity > QUANTITY_LIMIT) {
            throw new IllegalArgumentException("수량은 최대 " + QUANTITY_LIMIT + "개 까지 가능합니다.");
        }
        tableOrders.get(table).add(new Order(menu, quantity));
    }

    public static boolean isEmpty(Table table){
        return tableOrders.get(table).isEmpty();
    }

    public static List<Order> query(Table table) throws IllegalArgumentException {
        if (tableOrders.get(table).isEmpty()) {
            throw new IllegalArgumentException("등록 된 주문이 없는 테이블입니다.");
        }
        return tableOrders.get(table);
    }

    public static void clear(Table table) {
        tableOrders.get(table).clear();
    }
}
