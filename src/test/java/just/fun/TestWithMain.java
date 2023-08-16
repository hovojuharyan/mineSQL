package just.fun;

import just.fun.column.Column;
import just.fun.column.ColumnType;

public class TestWithMain {
    private static final Engine ENGINE = new Engine();

    public static void main(String[] args) {
        testCreateTable();
        testInsert();
        testDropTable();
    }

    private static void testCreateTable() {
        CommandDto createCommand = CommandDto.builder("CREATE_TABLE", "person")
                .aColumn(new Column("name", ColumnType.STRING))
                .aColumn(new Column("surname", ColumnType.STRING))
                .aColumn(new Column("age", ColumnType.INT))
                .aColumn(new Column("isMarried", ColumnType.BOOL))
                .build();
        ENGINE.execute(createCommand);
    }

    private static void testDropTable() {
        CommandDto dropCommand = CommandDto.builder("DROP_TABLE", "person").build();
        ENGINE.execute(dropCommand);
    }

    private static void testInsert() {
        CommandDto insertCommand1 = CommandDto.builder("INSERT_INTO", "person")
                .aColumn(new Column("name", ColumnType.STRING))
                .aColumn(new Column("surname", ColumnType.STRING))
                .aColumn(new Column("age", ColumnType.INT))
                .aColumn(new Column("isMarried", ColumnType.BOOL))
                .aValue("poxos")
                .aValue("poxosyan")
                .aValue("25")
                .aValue("TRUE")
                .build();
        CommandDto insertCommand2 = CommandDto.builder("INSERT_INTO", "person")
                .aColumn(new Column("name", ColumnType.STRING))
                .aColumn(new Column("surname", ColumnType.STRING))
                .aColumn(new Column("age", ColumnType.INT))
                .aColumn(new Column("isMarried", ColumnType.BOOL))
                .aValue("petros")
                .aValue("petrosyan")
                .aValue("30")
                .aValue("TRUE")
                .build();
        CommandDto insertCommand3 = CommandDto.builder("INSERT_INTO", "person")
                .aColumn(new Column("brrra", ColumnType.STRING))
                .aColumn(new Column("surname", ColumnType.STRING))
                .aColumn(new Column("age", ColumnType.INT))
                .aColumn(new Column("isMarried", ColumnType.BOOL))
                .aValue("martiros")
                .aValue("martirosyan")
                .aValue("35")
                .aValue("FALSE")
                .build();
        CommandDto insertCommand4 = CommandDto.builder("INSERT_INTO", "person")
                .aColumn(new Column("name", ColumnType.STRING))
                .aColumn(new Column("surname", ColumnType.STRING))
                .aColumn(new Column("age", ColumnType.INT))
                .aColumn(new Column("isMarried", ColumnType.BOOL))
                .aValue("martiros")
                .aValue("martirosyan")
                .aValue("35")
                .build();
        ENGINE.execute(insertCommand1);
        ENGINE.execute(insertCommand2);
        ENGINE.execute(insertCommand3);
        ENGINE.execute(insertCommand4);
    }
}
