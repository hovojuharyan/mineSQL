package just.fun.domain.schema.condition;

import just.fun.domain.schema.Column;

import java.util.function.Predicate;

public class And implements Testable {
    private final Predicate<Object> predicate;
    private final Column<Object> column;

    private And(Column<Object> column, Predicate<Object> predicate) {
        this.column = column;
        this.predicate = predicate;
    }

    @SuppressWarnings("unchecked")
    public static <T> And of(Column<T> column, Predicate<T> predicate) {
        return new And((Column<Object>) column, (Predicate<Object>) predicate);
    }

    @Override
    public boolean test(boolean previous, Object value) {
        return previous && predicate.test(value);
    }

    @Override
    public Column<Object> getColumn() {
        return column;
    }
}
