package just.fun.domain.schema;

import just.fun.domain.schema.types.ColumnType;

import java.util.Objects;

public class Column<RT> {

    private final String name;
    private final ColumnType<RT> type;

    public Column(String name, ColumnType<RT> columnType) {
        this.name = name;
        this.type = columnType;
    }

    public static <T> Column<T> with(String name, ColumnType<T> type) {
        return new Column<>(name, type);
    }

    public String name() {
        return name;
    }

    public ColumnType<RT> type() {
        return type;
    }

    public RT parse(String text) {
        return type.getValue(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column<?> column)) return false;
        return Objects.equals(name, column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
