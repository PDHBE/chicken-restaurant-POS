package ui.view;

import ui.controller.exception.NoFunctionException;
import ui.controller.OrderController;

import java.util.Scanner;

public class MainView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void exec() {
        printMain();
        int inputtedFunctionNumber = inputFunctionNumberUntilValid();
        OrderController.execFunction(inputtedFunctionNumber);
    }

    private static int inputFunctionNumberUntilValid() {
        while (true) {
            try {
                int inputtedFunctionNumber = inputFunctionNumber();
                OrderController.checkFunctionNumber(inputtedFunctionNumber);
                return inputtedFunctionNumber;
            } catch (NoFunctionException noFunctionException) {
                System.out.println("존재하지 않는 기능입니다.");
            }
        }
    }

    private static void printMain() {
        System.out.println("## 메인화면\n" +
                "1 - 주문등록\n" +
                "2 - 결제하기\n" +
                "3 - 프로그램 종료\n");
    }

    private static int inputFunctionNumber() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.nextInt();
    }
}
