package just.fun.domain.testdata;

import just.fun.domain.schema.*;
import just.fun.domain.schema.types.Bool;
import just.fun.domain.schema.types.Numeric;
import just.fun.domain.schema.types.Textual;

import java.util.List;

public class PersonData {

    public static Metadata metadata() {
        return new Metadata(tableName(), columns());
    }

    public static Columns columns() {
        Columns columns = new Columns();
        columns.add(nameColumn());
        columns.add(surnameColumn());
        columns.add(nationalityColumn());
        columns.add(ageColumn());
        columns.add(isMarriedColumn());
        return columns;
    }

    public static String tableName() {
        return "person";
    }

    public static Column<String> nameColumn() {
        return Column.with("name", new Textual());
    }

    public static Column<String> surnameColumn() {
        return Column.with("surname", new Textual());
    }

    public static Column<String> nationalityColumn() {
        return Column.with("nationality", new Textual());
    }

    public static Column<Integer> ageColumn() {
        return Column.with("age", new Numeric());
    }

    public static Column<Boolean> isMarriedColumn() {
        return Column.with("isMarried", new Bool());
    }

    public static Row aRow(String... values) {
        return Row.initRow(PersonData.columns(), List.of(values));
    }

    public static Data someData(List<Row> rows) {
        return new Data(rows);
    }

}
