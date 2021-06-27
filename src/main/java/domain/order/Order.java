package domain.order;

import domain.input.Quantity;
import domain.order.menu.Menu;

public class Order {
    private final Menu menu;
    private final Quantity quantity;

    public Order(Menu menu, Quantity quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public Quantity getQuantity() {
        return quantity;
    }
}
