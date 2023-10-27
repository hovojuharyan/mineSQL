package just.fun.domain.schema.condition;

import java.util.Objects;
import java.util.function.Predicate;

public class Conditions {

    public static <T extends Comparable<T>> Predicate<T> isNull() {
        return Objects::isNull;
    }

    public static <T extends Comparable<T>> Predicate<T> isNotNull() {
        return Objects::nonNull;
    }

    public static <T extends Comparable<T>> Predicate<T> isEqual(T conditional) {
        Objects.requireNonNull(conditional, "Consider using  isNull()");
        return actual -> actual != null && actual.compareTo(conditional) == 0;
    }

    public static <T extends Comparable<T>> Predicate<T> isNotEqual(T conditional) {
        Objects.requireNonNull(conditional, "Consider using  isNotNull()");
        return actual -> actual == null || actual.compareTo(conditional) != 0;
    }

    public static <T extends Comparable<T>> Predicate<T> isLessThan(T conditional) {
        Objects.requireNonNull(conditional);
        return actual -> actual != null && actual.compareTo(conditional) < 0;
    }

    public static <T extends Comparable<T>> Predicate<T> isGreaterThan(T conditional) {
        Objects.requireNonNull(conditional);
        return actual -> actual != null && actual.compareTo(conditional) > 0;
    }

    public static <T extends Comparable<T>> Predicate<T> isLessThanOrEqual(T conditional) {
        return isLessThan(conditional).or(isEqual(conditional));
    }

    public static <T extends Comparable<T>> Predicate<T> isGreaterThanOrEqual(T conditional) {
        return isGreaterThan(conditional).or(isEqual(conditional));
    }

    public static <T extends Comparable<T>> Predicate<T> tautology() {
        return actual -> true;
    }

    public static <T extends Comparable<T>> Predicate<T> contradiction() {
        return actual -> false;
    }
}
