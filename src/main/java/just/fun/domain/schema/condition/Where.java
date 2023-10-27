package just.fun.domain.schema.condition;

import just.fun.domain.schema.Row;

import java.util.LinkedList;
import java.util.List;

public class Where {
    private final List<Testable> testables;

    private Where() {
        this.testables = new LinkedList<>();
    }

    public static Where none() {
        return new Where();
    }

    public static Where begin(Testable first) {
        return new Where().add(first);
    }

    public Where add(Testable testable) {
        testables.add(testable);
        return this;
    }

    public boolean testForRow(Row row) {
        boolean previous = true;
        for (Testable testable : testables) {
            previous = testable.test(previous, row.columnValue(testable.getColumn()));
        }
        return previous;
    }
}
