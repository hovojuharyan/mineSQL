package just.fun.domain.schema;

import java.util.List;

public class Columns {
    private final List<Column<Object>> columns;

    public Columns(List<Column<Object>> columns) {
        this.columns = columns;
    }

    public List<Column<Object>> all() {
        return columns;
    }

    public <RT> void add(Column<RT> column) {
        columns.add((Column<Object>) column);
    }
}
