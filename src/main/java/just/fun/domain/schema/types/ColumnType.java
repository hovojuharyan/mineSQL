package just.fun.domain.schema.types;

public sealed interface ColumnType<RT> permits Numeric, Textual, Bool {

    RT getValue(String textual);

    static Textual any() {
        return new Textual();
    }

    static ColumnType of(String type) {
        return switch (type) {
            case "NUMERIC" -> new Numeric();
            case "BOOL" -> new Bool();
            case "TEXTUAL" -> new Textual();
            default -> throw new IllegalStateException("Invalid type " + type);
        };
    }
}
