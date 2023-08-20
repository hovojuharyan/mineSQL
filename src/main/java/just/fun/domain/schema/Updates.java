package just.fun.domain.schema;

import java.util.Map;

public class Updates {
    private final Map<Column<Object>, Object> updates;

    public Updates(Map<Column<Object>, Object> updates) {
        this.updates = updates;
    }

    public Map<Column<Object>, Object> all() {
        return updates;
    }

    public <RT> void add(Column<RT> column, RT newValue) {
        updates.put((Column<Object>) column, newValue);
    }

}
