package just.fun;

public class TestWithMain {
    public static void main(String[] args) {
        CommandDto createCommand = CommandDto.builder("CREATE_TABLE", "person")
                .aColumn(new Column("name", ColumnType.STRING))
                .aColumn(new Column("surname", ColumnType.STRING))
                .aColumn(new Column("age", ColumnType.INT))
                .aColumn(new Column("isMarried", ColumnType.BOOL))
                .build();
        CommandDto dropCommand = CommandDto.builder("DROP_TABLE", "person").build();

        Engine engine = new Engine();
        engine.execute(createCommand);
//        engine.execute(dropCommand);
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
                .aValue("OHOH")
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
        engine.execute(insertCommand1);
        engine.execute(insertCommand2);
        engine.execute(insertCommand3);
        engine.execute(insertCommand4);
    }
}
