package just.fun.domain.schema.unique;

import just.fun.serialization.SerialContent;

import java.util.*;

public class UniqueConstraintsSet implements SerialContent {
    private final Map<UniqueConstraint, Set<UniqueValues>> uniqueValuesMap;

    private UniqueConstraintsSet() {
        this.uniqueValuesMap = new HashMap<>();
    }

    public static UniqueConstraintsSet empty() {
        return new UniqueConstraintsSet();
    }

    public static UniqueConstraintsSet single(UniqueConstraint uniqueConstraint) {
        UniqueConstraintsSet uniqueConstraintsSet = new UniqueConstraintsSet();
        uniqueConstraintsSet.uniqueValuesMap.put(uniqueConstraint, new HashSet<>());
        return uniqueConstraintsSet;
    }

    public static UniqueConstraintsSet bunch(List<UniqueConstraint> uniqueConstraints) {
        UniqueConstraintsSet uniqueConstraintsSet = new UniqueConstraintsSet();
        for (UniqueConstraint uniqueConstraint : uniqueConstraints) {
            uniqueConstraintsSet.uniqueValuesMap.put(uniqueConstraint, new HashSet<>());
        }
        return uniqueConstraintsSet;
    }

    public void merge(UniqueConstraint uniqueConstraint, UniqueValues values) {
        uniqueValuesMap.merge(uniqueConstraint, Set.of(values), (o, n) -> {
            o.add(values);
            return o;
        });
    }

    public UniqueConstraintsSet add(UniqueConstraint uniqueConstraint) {
        uniqueValuesMap.put(uniqueConstraint, new HashSet<>());
        return this;
    }

    public boolean alreadyPresent(UniqueConstraint uniqueConstraint, UniqueValues values) {
        return uniqueValuesMap.containsKey(uniqueConstraint)
                && uniqueValuesMap.get(uniqueConstraint).contains(values);
    }

    public Set<UniqueConstraint> uniqueConstraints() {
        return uniqueValuesMap.keySet();
    }

    @Override
    public String asString() {
        return uniqueValuesMap.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UniqueConstraintsSet other)) return false;
        return Objects.equals(this.uniqueValuesMap, other.uniqueValuesMap);
    }
}
