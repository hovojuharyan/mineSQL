package just.fun.domain.schema.condition;

import just.fun.domain.schema.Column;

public interface Testable {
    boolean test(boolean previous, Object value);
    Column<Object> getColumn();
}
