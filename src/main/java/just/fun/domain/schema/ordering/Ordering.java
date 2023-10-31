package just.fun.domain.schema.ordering;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Row;

import java.util.Comparator;

public record Ordering(Comparator<Row> comparator) {

    public static Ordering none() {
        return new Ordering((r1, r2) -> 0);
    }

    public static <T extends Comparable<T>> Ordering byAsc(Column<T> column) {
        return new Ordering(Comparator.comparing(row -> row.columnValue(column)));
    }

    public static <T extends Comparable<T>> Ordering byDesc(Column<T> column) {
        Comparator<Row> comparing = Comparator.comparing(row -> row.columnValue(column));
        return new Ordering(comparing.reversed());
    }

    public <T extends Comparable<T>> Ordering thenAsc(Column<T> column) {
        return new Ordering(comparator.thenComparing(row -> row.columnValue(column)));
    }

    public <T extends Comparable<T>> Ordering thenDesc(Column<T> column) {
        return new Ordering(comparator.thenComparing(row -> row.columnValue(column)).reversed());
    }
}
