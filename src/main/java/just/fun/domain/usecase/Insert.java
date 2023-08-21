package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;

public class Insert implements Command {
    private final String tableName;
    private final Row row;
    private final RowSerializer rowSerializer;

    public Insert(String tableName, Row row, RowSerializer rowSerializer) {
        this.tableName = tableName;
        this.row = row;
        this.rowSerializer = rowSerializer;
    }

    @Override
    public Response run() {
        rowSerializer.serialize(tableName, row);
        return Response.ok("INSERTED " + row);
    }

}
