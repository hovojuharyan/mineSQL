package just.fun.domain.error;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.unique.UniqueConstraint;

import java.util.stream.Collectors;

public class UniqueColumnConstraintViolationException extends RuntimeException {
    public UniqueColumnConstraintViolationException(UniqueConstraint uniqueConstraint) {
        super("Violated Unique Constraint for column(s) " +
                uniqueConstraint.columnSet()
                        .stream()
                        .map(Column::name)
                        .collect(Collectors.joining(", ")));
    }
}
