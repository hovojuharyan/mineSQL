package just.fun.domain.schema;

import java.util.function.Predicate;

public class Condition<T> {
    private final Column<T> column;
    private final Predicate<T> predicate;

    public Condition(Column<T> column, T conditionalValue) {
        this.column = column;
        this.predicate = actual -> actual.equals(conditionalValue);
    }

    public boolean test(T actual) {
        return predicate.test(actual);
    }

    public Column<T> getColumn() {
        return column;
    }
}
