package just.fun.domain.schema;

import java.util.ArrayList;
import java.util.List;

public class Conditions {
    private final List<Condition<Object>> conditions;

    public Conditions() {
        this.conditions = new ArrayList<>();
    }

    public List<Condition<Object>> all() {
        return new ArrayList<>(conditions);
    }

    public <T> void add(Condition<T> condition) {
        conditions.add((Condition<Object>) condition);
    }

}
