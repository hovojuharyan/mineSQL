package just.fun.domain.schema.condition;

import just.fun.domain.schema.Column;

import java.util.function.Predicate;

public class Or implements Testable {
    private final Predicate<Object> predicate;
    private final Column<Object> column;

    private Or(Column<Object> column, Predicate<Object> predicate) {
        this.column = column;
        this.predicate = predicate;
    }

    @SuppressWarnings("unchecked")
    public static <T> Or of(Column<T> column, Predicate<T> predicate) {
        return new Or((Column<Object>) column, (Predicate<Object>) predicate);
    }

    @Override
    public boolean test(boolean previous, Object value) {
        return previous || predicate.test(value);
    }

    @Override
    public Column<Object> getColumn() {
        return column;
    }
}
