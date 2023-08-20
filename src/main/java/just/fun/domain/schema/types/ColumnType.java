package just.fun.domain.schema.types;

public sealed interface ColumnType<RT> permits Numeric, Textual, Bool {

    RT getValue(String textual);

    static Textual any() {
        return new Textual();
    }
}
