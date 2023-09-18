package just.fun.domain.schema;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Condition<T> {
    Column<T> column;
    Predicate<T> predicate;
    T conditionalValue;

    public Condition(Column<T> column, T conditionalValue) {
        this.column = column;
        this.conditionalValue = conditionalValue;
        this.predicate = actual -> actual.equals(conditionalValue);
    }

    public boolean test(T actual) {
        return predicate.test(actual);
    }

    public Column<T> getColumn() {
        return column;
    }
}
