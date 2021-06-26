import service.FunctionNumber;
import service.PaymentCalculator;
import service.PaymentType;
import service.TableOrders;
import domain.*;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    private static final List<Table> tables = TableRepository.tables();
    private static final List<Menu> menus = MenuRepository.menus();

    public static void main(String[] args) {
        while (true) {
            FunctionNumber functionNumber = selectFunctionNumberUntilValid();
            if (functionNumber.isRegister()) {
                register();
                continue;
            }
            if (functionNumber.isPay()) {
                pay();
                continue;
            }
            if (functionNumber.isExit()) {
                break;
            }
        }
    }

    private static FunctionNumber selectFunctionNumberUntilValid() {
        while (true) {
            try {
                OutputView.printMain();
                return new FunctionNumber(InputView.inputFunctionNumber());
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    private static void pay() {
        Table tableWithOrders = selectTableWithOrders();
        List<Order> orders = TableOrders.query(tableWithOrders);

        OutputView.printPayStart(tableWithOrders);
        PaymentType paymentType = selectPaymentTypeUntilValid();
        int payment = PaymentCalculator.calculate(orders, paymentType);
        OutputView.printPayment(payment);

        TableOrders.clear(tableWithOrders);
    }

    private static PaymentType selectPaymentTypeUntilValid() {
        while (true) {
            try {
                return new PaymentType(InputView.inputPaymentType());
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    private static Table selectTableWithOrders() {
        while (true) {
            try {
                Table table = selectTableUntilValid();
                List<Order> orders = TableOrders.query(table);
                OutputView.printOrders(orders);
                return table;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    private static void register() {
        Table table = selectTableUntilValid();
        Menu menu = selectMenuUntilValid();
        while (true) {
            try {
                TableOrders.register(table, menu, InputView.inputQuantity());
                return;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    private static Menu selectMenuUntilValid() {
        while (true) {
            try {
                OutputView.printMenus(menus);
                return MenuRepository.findByNumber(InputView.inputMenuNumber());
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }

    private static Table selectTableUntilValid() {
        while (true) {
            try {
                OutputView.printTables(tables);
                return TableRepository.findByNumber(InputView.inputTableNumber());
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                System.out.println("다시 입력해주세요.");
            }
        }
    }
}
