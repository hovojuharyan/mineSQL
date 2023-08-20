package just.fun.domain.schema;

import java.util.ArrayList;
import java.util.List;

public class Conditions {
    private final List<Condition<Object>> conditions;

    public Conditions(List<Condition<Object>> conditions) {
        this.conditions = new ArrayList<>(conditions);
    }

    public List<Condition<Object>> all() {
        return new ArrayList<>(conditions);
    }

    public <RT> void add(Condition<RT> condition) {
        conditions.add((Condition<Object>) condition);
    }
}
