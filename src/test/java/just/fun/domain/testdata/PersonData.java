package just.fun.domain.testdata;

import just.fun.domain.schema.*;
import just.fun.domain.schema.types.Bool;
import just.fun.domain.schema.types.Numeric;
import just.fun.domain.schema.types.Textual;

import java.util.List;

public class PersonData {

    public static Metadata metadata() {
        String tableName = "person";
        return new Metadata(tableName, columns());
    }

    public static Columns columns() {
        Columns columns = new Columns();
        columns.add(new Column<>("name", new Textual()));
        columns.add(new Column<>("surname", new Textual()));
        columns.add(new Column<>("nationality", new Textual()));
        columns.add(new Column<>("age", new Numeric()));
        columns.add(new Column<>("isMarried", new Bool()));
        return columns;
    }

    public static Row aRow(String... values) {
        return Row.initRow(PersonData.columns(), List.of(values));
    }

    public static Data someData(List<Row> rows) {
        return new Data(rows);
    }

}
