package just.fun.domain.schema;

import just.fun.domain.schema.types.ColumnType;

import java.util.Objects;

public class Column<RT> {

    private final String name;
    private final ColumnType<RT> type;

    public Column(String name, ColumnType columnType) {
        this.name = name;
        this.type = columnType;
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

    public boolean tryCondition(String actualValue, Condition<RT> condition) {
        RT parsedActualValue = parse(actualValue);
        return condition.test(parsedActualValue);
    }

    public static Column<?> withName(String name) {
        return new Column<>(name, ColumnType.any());
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
