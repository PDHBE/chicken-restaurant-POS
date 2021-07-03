package ui.view;

import ui.controller.exception.NoMenuException;
import ui.controller.exception.NoTableException;
import ui.controller.exception.QuantityLimitOverException;
import domain.model.Menu;
import domain.model.Table;
import ui.controller.OrderController;

import java.util.List;
import java.util.Scanner;

public class RegisterView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE_NON_ORDER = "└ ─ ┘";
    private static final String BOTTOM_LINE_IS_ORDER = "└ ₩ ┘";

    public static void exec(List<Table> allTables, List<Table> orderedTables, List<Menu> menus) {
        printTables(allTables,orderedTables);
        int tableNumber = inputTableNumberUntilValid();
        printMenus(menus);
        int menuNumber = inputMenuNumberUntilValid();
        int quantity = inputQuantityUntilValid();
        OrderController.register(tableNumber, menuNumber, quantity);
    }

    private static int inputQuantityUntilValid() {
        while (true) {
            try {
                int inputtedQuantity = inputQuantity();
                OrderController.checkQuantity(inputtedQuantity);
                return inputtedQuantity;
            } catch (QuantityLimitOverException quantityLimitOverException) {
                System.out.println("수량 제한을 초과하였습니다.");
            }
        }
    }

    private static int inputMenuNumberUntilValid() {
        while (true) {
            try {
                int inputtedMenuNumber = inputMenuNumber();
                OrderController.checkMenuNumber(inputtedMenuNumber);
                return inputtedMenuNumber;
            } catch (NoMenuException noMenuException) {
                System.out.println("존재하지 않는 메뉴입니다.");
            }
        }
    }

    private static int inputTableNumberUntilValid() {
        while (true) {
            try {
                int inputtedTableNumber = inputTableNumber();
                OrderController.checkTableNumber(inputtedTableNumber);
                return inputtedTableNumber;
            } catch (NoTableException noTableException) {
                System.out.println("존재하지 않는 테이블입니다.");
            }
        }
    }

    private static void printTables(List<Table> allTables, List<Table> orderedTables) {
        System.out.println("## 테이블 목록");
        final int size = allTables.size();
        printLine(TOP_LINE, size);
        printTableNumbers(allTables);
        printBottom(allTables, orderedTables);
        System.out.println();
    }

    private static void printMenus(List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
        System.out.println();
    }

    private static int inputMenuNumber() {
        System.out.println("## 등록 할 메뉴를 선택하세요.");
        return scanner.nextInt();
    }

    private static int inputQuantity() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        return scanner.nextInt();
    }


    private static int inputTableNumber() {
        System.out.println("## 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    private static void printBottom(List<Table> allTables, List<Table> orderedTables) {
        for (Table table : allTables) {
            if (orderedTables.contains(table)) {
                System.out.print(BOTTOM_LINE_IS_ORDER);
                continue;
            }
            System.out.print(BOTTOM_LINE_NON_ORDER);
        }
        System.out.println();
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
