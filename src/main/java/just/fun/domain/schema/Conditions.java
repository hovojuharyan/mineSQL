package just.fun.domain.schema;

import java.util.List;

public class Conditions {
    private final List<Condition<Object>> conditions;

    public Conditions(List<Condition<Object>> conditions) {
        this.conditions = conditions;
    }

    public List<Condition<Object>> all() {
        return conditions;
    }

    public <RT> void add(Condition<RT> condition) {
        conditions.add((Condition<Object>) condition);
    }
}
