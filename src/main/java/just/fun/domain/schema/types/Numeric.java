package just.fun.domain.schema.types;

public final class Numeric implements ColumnType<Integer> {

    private final int value;
    
    public Numeric(String textual) {
        try {
            value = Integer.parseInt(textual);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public Numeric(int  value) {
        this.value = value;
    }

    public static Numeric of(String textual) {
        return new Numeric(textual);
    }

    public static Numeric of(int value) {
        return new Numeric(value);
    }

    @Override
    public Integer getValue(String textual) {
        return Integer.parseInt(textual);
    }

    @Override
    public String toString() {
        return value + "";
    }
}
