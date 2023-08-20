package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Row implements SerialContent<Row> {

    private final Map<Column<Object>, Object> cells;

    private Row() {
        cells = new HashMap<>();
    }

    public Map<Column<Object>, Object> cells() {
        return cells;
    }

    public <RT> RT columnValue(Column<RT> column) {
        return (RT) cells.get(column);
    }

    public <RT> void addCell(Column<RT> column, RT value) {
        cells.put((Column<Object>) column, value);
    }

    public <RT> void updateCell(Column<RT> column, RT newValue) {
        cells.put((Column<Object>) column, newValue);
    }

    public static Row fullRow(Columns actualColumns,
                              Map<Column<Object>, Object> givenCells) {
        Row row = new Row();
        for (Column<Object> column : actualColumns.all()) {
            row.cells.put(column, givenCells.getOrDefault(column, null));
        }
        return row;
    }

    @Override
    public String serialForm() {
        return cells.values()
                .stream().map(Object::toString)
                .collect(Collectors.joining("|"));
    }

}
