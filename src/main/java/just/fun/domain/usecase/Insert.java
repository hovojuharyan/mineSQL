package just.fun.domain.usecase;

import just.fun.domain.error.NotNullConstraintViolatedExcpetion;
import just.fun.domain.error.UniqueColumnConstraintViolationException;
import just.fun.domain.response.Response;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;
import just.fun.serialization.UniqueConstraintsSetSerializer;

import java.util.List;

public class Insert implements Command {
    private final String tableName;
    private final RowSerializer rowSerializer;
    private final Columns columns;
    private final List<String> values;
    private final UniqueConstraintsSetSerializer uniqueConstraintsSetSerializer;

    public Insert(String tableName,
                  RowSerializer rowSerializer,
                  Columns columns,
                  List<String> values,
                  UniqueConstraintsSetSerializer uniqueConstraintsSetSerializer) {
        this.tableName = tableName;
        this.rowSerializer = rowSerializer;
        this.columns = columns;
        this.values = values;
        this.uniqueConstraintsSetSerializer = uniqueConstraintsSetSerializer;
    }

    @Override
    public Response run() {
        try {
            Row row = Row.initRow(columns, values);
            row.handleUniqueConstraints(uniqueConstraintsSetSerializer.deserialize(tableName));
            rowSerializer.serialize(tableName, row);
            return Response.ok("INSERTED " + row);
        } catch (NotNullConstraintViolatedExcpetion|UniqueColumnConstraintViolationException e) {
            return Response.error(e);
        }
    }

}
