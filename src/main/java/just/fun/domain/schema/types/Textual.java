package just.fun.domain.schema.types;

public final class Textual implements ColumnType<String> {
    @Override
    public String getValue(String textual) {
        return textual;
    }

    @Override
    public String toString() {
        return "TEXTUAL";
    }
}
