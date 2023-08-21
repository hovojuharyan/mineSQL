package just.fun.domain.schema.types;

public final class Numeric implements ColumnType<Integer> {

    @Override
    public Integer getValue(String textual) {
        return Integer.parseInt(textual);
    }

    @Override
    public String toString() {
        return "NUMERIC";
    }
}
