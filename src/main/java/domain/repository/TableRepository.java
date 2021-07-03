package domain.repository;

import ui.controller.exception.NoTableException;
import domain.model.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();

    static {
        tables.add(new Table(1));
        tables.add(new Table(2));
        tables.add(new Table(3));
        tables.add(new Table(5));
        tables.add(new Table(6));
        tables.add(new Table(8));
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static Table findByNumber(int number) {
        for (Table table : tables) {
            if(table.getNumber() == number){
                return table;
            }
        }
        throw new NoTableException();
    }
}
