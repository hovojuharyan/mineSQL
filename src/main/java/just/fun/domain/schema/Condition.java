package just.fun.domain.schema;

import java.util.function.BiPredicate;

public class Condition<T> {
    Column<T> column;
    BiPredicate<T, T> biPredicate;
    T conditionalValue;

    public Condition(Column<T> column, BiPredicate<T, T> biPredicate) {
        this.column = column;
        this.biPredicate = biPredicate;
    }

    public boolean test(T actual) {
        return biPredicate.test(actual, conditionalValue);
    }

    public Column<T> getColumn() {
        return column;
    }

    public BiPredicate<T, T> getBiPredicate() {
        return biPredicate;
    }
}
