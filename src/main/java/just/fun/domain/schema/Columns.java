package just.fun.domain.schema;

import java.util.ArrayList;
import java.util.List;

public class Columns {
    private final List<Column<Object>> columns;

    public Columns() {
        columns = new ArrayList<>();
    }

    private Columns(List<Column<Object>> columns) {
        this.columns = columns;
    }

    public static Columns empty() {
        return new Columns(new ArrayList<>());
    }

    public List<Column<Object>> all() {
        return new ArrayList<>(columns);
    }

    public <RT> Columns add(Column<RT> column) {
        columns.add((Column<Object>) column);
        return this;
    }
}
