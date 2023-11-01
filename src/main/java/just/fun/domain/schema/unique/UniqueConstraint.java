package just.fun.domain.schema.unique;

import just.fun.domain.schema.Column;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class UniqueConstraint {
    private final List<Column<Object>> columnSet;

    public static <T> UniqueConstraint of(Column<T> column) {
        UniqueConstraint uniqueConstraint = new UniqueConstraint();
        uniqueConstraint.columnSet.add((Column<Object>) column);
        return uniqueConstraint;
    }

    private UniqueConstraint() {
        this.columnSet = new ArrayList<>();
    }

    public List<Column<Object>> columnSet() {
        return columnSet;
    }

    public <T> UniqueConstraint add(Column<T> column) {
        columnSet.add((Column<Object>) column);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (UniqueConstraint) obj;
        return Objects.equals(this.columnSet, that.columnSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnSet);
    }

    @Override
    public String toString() {
        return columnSet.toString();
    }
}
