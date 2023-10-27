package just.fun.domain.schema;

import just.fun.domain.schema.types.ColumnType;

import java.util.Objects;

public class Column<RT> {

    private final String name;
    private final ColumnType<RT> type;
    private final boolean nullable;

    private Column(String name, ColumnType<RT> columnType, boolean nullable) {
        this.name = name;
        this.type = columnType;
        this.nullable = nullable;
    }

    public static <T> Column<T> with(String name, ColumnType<T> type) {
        return new Column<>(name, type, true);
    }

    public static <T> Column<T> notNull(String name, ColumnType<T> type) {
        return new Column<>(name, type, false);
    }

    public String name() {
        return name;
    }

    public ColumnType<RT> type() {
        return type;
    }

    public boolean isNullable() {
        return nullable;
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
