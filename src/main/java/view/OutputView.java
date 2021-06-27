package view;

import domain.order.menu.Menu;
import domain.order.Order;
import domain.table.Table;
import domain.tableorders.TableOrders;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE_NON_ORDER = "└ ─ ┘";
    private static final String BOTTOM_LINE_IS_ORDER = "└ ₩ ┘";

    public static void printPayment(int payment){
        System.out.println("최종 결제 할 금액");
        System.out.println(payment);
    }

    public static void printPayStart(final Table table){
        System.out.println("## " + table.getNumber() + "번 테이블의 결제를 진행합니다.");
    }

    public static void printOrders(final List<Order> orders){
        System.out.println("메뉴  수량  금액");
        for (Order order : orders) {
            String menuName = order.getMenu().getName();
            int quantity = Integer.parseInt(order.getQuantity().toString());
            int amount = order.getMenu().getPrice() * quantity;
            System.out.println(menuName + "  " + quantity + "  " + amount);
        }
    }

    public static void printMain(){
        System.out.println("## 메인화면\n" +
                "1 - 주문등록\n" +
                "2 - 결제하기\n" +
                "3 - 프로그램 종료\n\n");
    }

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(tables);
        printBottom(tables);
    }

    private static void printBottom(List<Table> tables) {
        for (Table table : tables) {
            if(TableOrders.isEmpty(table)){
                System.out.print(BOTTOM_LINE_NON_ORDER);
                continue;
            }
            System.out.print(BOTTOM_LINE_IS_ORDER);
        }
        System.out.println();
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printLine(final String line, final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(line);
        }
        System.out.println();
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table);
        }
        System.out.println();
    }
}
