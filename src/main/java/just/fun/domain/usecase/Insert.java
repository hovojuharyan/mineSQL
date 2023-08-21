package just.fun.domain.usecase;

import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;

public class Insert {
    Row row;
    RowSerializer rowSerializer;

    public Insert(Row row, RowSerializer rowSerializer) {
        this.row = row;
        this.rowSerializer = rowSerializer;
    }

    public void run() {
        rowSerializer.serialize(row);
    }

}
