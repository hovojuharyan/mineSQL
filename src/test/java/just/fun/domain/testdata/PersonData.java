package just.fun.domain.testdata;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.types.Bool;
import just.fun.domain.schema.types.Numeric;
import just.fun.domain.schema.types.Textual;

import java.util.List;

public class PersonData {

    public static Metadata personMetadata() {
        String tableName = "person";
        Columns columns = new Columns(List.of(
                new Column("name", new Textual()),
                new Column("surname", new Textual()),
                new Column("age", new Numeric()),
                new Column("isMarried", new Bool())
        ));
        return new Metadata(tableName, columns);
    }


}
