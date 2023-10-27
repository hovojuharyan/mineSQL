package just.fun.domain.schema;

import java.util.Objects;
import java.util.function.Predicate;

public class Condition<T> {
    private final Column<T> column;
    private final Predicate<T> predicate;

    private Condition(Column<T> column, Predicate<T> predicate) {
        this.column = column;
        this.predicate = predicate;
    }

    public static Condition<?> tautology(Column<?> column) {
        return new Condition<>(column, actual -> true);
    }

    public static <T extends Comparable<T>> Condition<T> isEqual(Column<T> column, T conditionalValue) {
        return new Condition<>(column, actual -> actual.equals(conditionalValue));
    }

    public static <T extends Comparable<T>> Condition<T> isLess(Column<T> column, T conditionalValue) {
        return new Condition<>(column, actual -> actual.compareTo(conditionalValue) < 0);
    }

    public static <T extends Comparable<T>> Condition<T> isLessOrEqual(Column<T> column, T conditionalValue) {
        return new Condition<>(column, actual -> actual.compareTo(conditionalValue) <= 0);
    }

    public static <T extends Comparable<T>> Condition<T> isBigger(Column<T> column, T conditionalValue) {
        return new Condition<>(column, actual -> actual.compareTo(conditionalValue) > 0);
    }

    public static <T extends Comparable<T>> Condition<T> isBiggerOrEqual(Column<T> column, T conditionalValue) {
        return new Condition<>(column, actual -> actual.compareTo(conditionalValue) >= 0);
    }

    public static <T extends Comparable<T>> Condition<T> isNotEqual(Column<T> column, T conditionalValue) {
        return new Condition<>(column, actual -> !actual.equals(conditionalValue));
    }

    public static <T extends Comparable<T>> Condition<T> isNull(Column<T> column) {
        return new Condition<>(column, Objects::isNull);
    }

    public static <T extends Comparable<T>> Condition<T> isNotNull(Column<T> column) {
        return new Condition<>(column, Objects::nonNull);
    }

    public boolean test(T actual) {
        return predicate.test(actual);
    }

    public Column<T> getColumn() {
        return column;
    }
}
