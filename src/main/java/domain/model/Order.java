package domain.model;

public class Order {
    private final Table table;
    private final Menu menu;
    private final Quantity quantity;

    public Order(Table table, Menu menu, Quantity quantity) {
        this.table = table;
        this.menu = menu;
        this.quantity = quantity;
    }

    public final int calculateAmount(){
        return menu.getPrice() * quantity.getValue();
    }

    public final boolean isChicken(){
        return menu.getCategory().equals(Category.CHICKEN);
    }

    public final boolean isTable(Table table){
        return this.table.equals(table);
    }

    @Override
    public String toString() {
        return menu.getName() + "  " + quantity.getValue() + "  " + calculateAmount();
    }

    public final int getQuantity(){
        return quantity.getValue();
    }
}
