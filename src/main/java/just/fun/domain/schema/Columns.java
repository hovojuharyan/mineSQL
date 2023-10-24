package just.fun.domain.schema;

import java.util.ArrayList;
import java.util.List;

public class Columns {
    private List<Column<Object>> columns;

    public Columns() {
        columns = new ArrayList<>();
    }

    public List<Column<Object>> all() {
        return new ArrayList<>(columns);
    }

    public <RT> void add(Column<RT> column) {
        columns.add((Column<Object>) column);
    }
}
