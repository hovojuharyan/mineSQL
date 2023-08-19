package just.fun.domain.usecase;

import just.fun.domain.schema.Row;
import just.fun.serialization.Serializer;

public class Insert {
    Row row;
    Serializer<Row> rowSerializer;

    public Insert(Row row, Serializer<Row> rowSerializer) {
        this.row = row;
        this.rowSerializer = rowSerializer;
    }

    public void run() {
        rowSerializer.serialize(row);
    }

}
