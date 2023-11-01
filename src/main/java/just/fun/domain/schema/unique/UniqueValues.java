package just.fun.domain.schema.unique;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UniqueValues {
    private final List<Object> uniqueValueList;

    public static UniqueValues single(Object value) {
        UniqueValues uniqueValues = new UniqueValues();
        uniqueValues.uniqueValueList.add(value);
        return uniqueValues;
    }

    public static UniqueValues bunch(Object... value) {
        UniqueValues uniqueValues = new UniqueValues();
        uniqueValues.uniqueValueList.addAll(List.of(value));
        return uniqueValues;
    }

    public static UniqueValues empty() {
        return new UniqueValues();
    }

    private UniqueValues() {
        this.uniqueValueList = new ArrayList<>();
    }

    public void add(Object value) {
        this.uniqueValueList.add(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return false;
        if (!(obj instanceof UniqueValues other)) return false;
        return Objects.equals(this.uniqueValueList, other.uniqueValueList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uniqueValueList);
    }

    @Override
    public String toString() {
        return uniqueValueList.toString();
    }
}
