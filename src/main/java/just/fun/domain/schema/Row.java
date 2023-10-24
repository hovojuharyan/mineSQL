package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Row implements SerialContent {

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

    public static Row fullRow(Columns actualColumns,
                              Map<Column<Object>, Object> givenCells) {
        Row row = new Row();
        for (Column<Object> column : actualColumns.all()) {
            row.cells.put(column, givenCells.getOrDefault(column, null));
        }
        return row;
    }

    public static Row initRow(Columns actualColumns, List<String> values) {
        List<Column<Object>> columnList = actualColumns.all();
        Row row = new Row();
        for (int i = 0; i < columnList.size(); i++) {
            row.addCell(columnList.get(i), columnList.get(i).parse(values.get(i)));
        }
        return row;
    }

    @Override
    public String asString() {
        return cells.values()
                .stream().map(Object::toString)
                .collect(Collectors.joining("-"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof Row other)) return false;
        return cells.equals(other.cells);
    }
}
