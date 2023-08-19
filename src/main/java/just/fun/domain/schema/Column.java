package just.fun.domain.schema;

import java.util.Objects;

public class Column {

    private final String name;

    public Column(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Column withName(String name) {
        return new Column(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Column column)) return false;
        return Objects.equals(name, column.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
