package just.fun.domain.schema;

import java.util.ArrayList;
import java.util.List;

public class Columns {
    private final List<Column<Object>> columns;

    public Columns(List<Column<Object>> columns) {
        this.columns = new ArrayList<>(columns);
    }

    public List<Column<Object>> all() {
        return new ArrayList<>(columns);
    }

    public <RT> void add(Column<RT> column) {
        columns.add((Column<Object>) column);
    }
}
