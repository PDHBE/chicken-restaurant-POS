package domain.input;

import java.util.Arrays;
import java.util.List;

public class FunctionNumber {
    private static final int REGISTER = 1;
    private static final int PAY = 2;
    private static final int EXIT = 3;
    private static final List<Integer> enableValues = Arrays.asList(REGISTER,PAY,EXIT);
    private final int value;

    public FunctionNumber(int value) throws IllegalArgumentException{
        if(!enableValues.contains(value)){
            throw new IllegalArgumentException("존재하지 않는 기능 번호입니다.");
        }
        this.value = value;
    }

    public boolean isRegister(){
        return value == REGISTER;
    }

    public boolean isPay(){
        return value == PAY;
    }

    public boolean isExit(){
        return value == EXIT;
    }
}
