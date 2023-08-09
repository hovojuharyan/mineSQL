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
        engine.execute(dropCommand);
    }
}
