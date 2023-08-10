package just.fun;

import java.util.Objects;

public record Column(String name, ColumnType type, int order) {
    public Column(String name, ColumnType type) {
        this(name, type, 0);
    }

    public static Column fromString(String textual) {
        String[] split = textual.split("-");
        return new Column(split[0], ColumnType.valueOf(split[1].toLowerCase()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Column other)) return false;
        return Objects.equals(name, other.name) && Objects.equals(type, other.type);
    }

    @Override
    public String toString() {
        return name + "-" + type;
    }
}
