package just.fun.domain.schema.group;

import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupRow {
    private final Row row;
    private final Columns grouping;

    private GroupRow(Row row, Columns grouping) {
        this.row = row;
        this.grouping = grouping;
    }

    public static GroupRow of(Row row, Columns grouping) {
        return new GroupRow(row, grouping);
    }

    public Row fullRow() {
        return row;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof GroupRow other)) return false;
        for (var column : grouping.all()) {
            if (row.columnValue(column) != other.row.columnValue(column)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        List<Object> values = new ArrayList<>();
        for (var column : grouping.all()) {
            values.add(row.columnValue(column));
        }
        return Objects.hashCode(values);
    }
}
