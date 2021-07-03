package ui.view;

import ui.controller.exception.NoOrderException;
import ui.controller.exception.NoPaymentTypeException;
import ui.controller.exception.NoTableException;
import domain.model.Table;
import ui.controller.OrderController;

import java.util.List;
import java.util.Scanner;

public class PayView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE_NON_ORDER = "└ ─ ┘";
    private static final String BOTTOM_LINE_IS_ORDER = "└ ₩ ┘";

    public static void exec(List<Table> allTables, List<Table> orderedTables) {
        printTables(allTables,orderedTables);
        int tableNumber = inputOrderedTableNumberUntilValid();
        int paymentType = inputPaymentTypeUntilValid();
        OrderController.pay(tableNumber, paymentType);
    }

    private static int inputPaymentTypeUntilValid() {
        while (true) {
            try {
                int inputtedPaymentType = inputPaymentType();
                OrderController.checkPaymentType(inputtedPaymentType);
                return inputtedPaymentType;
            } catch (NoPaymentTypeException noPaymentTypeException) {
                System.out.println("존재하지 않는 결제 수단입니다.");
            }
        }
    }

    private static int inputOrderedTableNumberUntilValid() {
        while (true) {
            try {
                int inputtedTableNumber = inputTableNumber();
                OrderController.checkOrderedTable(inputtedTableNumber);
                return inputtedTableNumber;
            } catch (NoTableException noTableException) {
                System.out.println("존재하지 않는 테이블입니다.");
            } catch (NoOrderException noOrderException) {
                System.out.println("주문이 없는 테이블입니다.");
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

    public static int inputTableNumber() {
        System.out.println("## 테이블을 선택하세요.");
        return scanner.nextInt();
    }

    public static int inputPaymentType() {
        System.out.println("## 신용 카드는 1번, 현금은 2번");
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
