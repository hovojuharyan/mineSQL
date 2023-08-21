package just.fun.defaults.fileio;

import just.fun.domain.usecase.Command;
import just.fun.domain.usecase.CreateTable;
import just.fun.maker.CommandMaker;
import just.fun.serialization.DataSerializer;
import just.fun.serialization.Dropper;
import just.fun.serialization.MetadataSerializer;
import just.fun.serialization.RowSerializer;

public class ConsoleCommandMaker implements CommandMaker<String> {

    private static final String CREATE_TABLE_PREFIX = "CREATE_TABLE";
    private static final String DROP_TABLE_PREFIX = "DROP_TABLE";
    private static final String INSERT_PREFIX = "INSERT_INTO";
    private static final String SELECT_PREFIX = "SELECT";
    private static final String UPDATE_PREFIX = "UPDATE";

    private final MetadataSerializer metadataSerializer;
    private final DataSerializer dataSerializer;
    private final RowSerializer rowSerializer;
    private final Dropper dropper;

    public ConsoleCommandMaker(MetadataSerializer metadataSerializer, DataSerializer dataSerializer,
                               RowSerializer rowSerializer, Dropper dropper) {
        this.metadataSerializer = metadataSerializer;
        this.dataSerializer = dataSerializer;
        this.rowSerializer = rowSerializer;
        this.dropper = dropper;
    }


    @Override
    public Command make(String input) {
        return null;
    }

    private CreateTable parseCreateTableCommand(String[] commandParts) {
        return null;
    }

    private CreateTable parseDropTableCommand(String tableName) {
        return null;
    }

    private CreateTable parseInsertCommand(String[] commandParts) {
        return null;
    }

    private CreateTable parseSelectQuery(String[] commandParts) {
        return null;
    }

    private CreateTable parseUpdateCommand(String[] commandParts) {
        return null;
    }
}
