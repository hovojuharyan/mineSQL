package just.fun;

public record Column(String name, ColumnType type) {
    public static Column fromString(String textual) {
        String[] split = textual.split("-");
        return new Column(split[0], ColumnType.valueOf(split[1].toLowerCase()));
    }

    @Override
    public String toString() {
        return name + "-" + type;
    }
}
