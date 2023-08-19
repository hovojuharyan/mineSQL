package just.fun.domain.schema;

public class Table {
    private final Metadata metadata;
    private final Data data;

    public Table(Metadata metadata, Data data) {
        this.metadata = metadata;
        this.data = data;
    }
}
