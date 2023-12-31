package just.fun.domain.schema.types;

public final class Bool implements ColumnType<Boolean> {
    @Override
    public Boolean getValue(String textual) {
        return "TRUE".equalsIgnoreCase(textual);
    }

    @Override
    public String toString() {
        return "BOOL";
    }
}
