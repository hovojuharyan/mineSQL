package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class Row implements SerialContent<Row> {

    private final Map<Column, String> cells;

    private Row() {
        cells = new HashMap<>();
    }

    public Map<Column, String> cells() {
        return cells;
    }

    public void addCell(Column column, String value) {
        cells.put(column, value);
    }

    public void updateCell(Column column, String newValue) {
        cells.put(column, newValue);
    }

    public boolean tryCondition(Column column, Predicate<String> predicate) {
        return predicate.test(cells.get(column));
    }

    public static Row fullRow(List<Column> actualColumns, Map<Column, String> givenCells) {
        Row row = new Row();
        for (Column column : actualColumns) {
            row.addCell(column, givenCells.getOrDefault(column, null));
        }
        return row;
    }

    @Override
    public String serialForm() {
        return String.join("|", cells.values());
    }

}
