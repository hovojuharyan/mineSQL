package just.fun.serial;

import just.fun.column.ColumnsDefinition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ColumnsDefinitionFileDeserializer implements ColumnsDefinitionDeserializer {

    private final static String ROOT_DIR = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";
    private final Path path;

    public ColumnsDefinitionFileDeserializer(String tableName) {
        path = Path.of(ROOT_DIR + "tbl_metadata_" + tableName + ".txt");
    }

    @Override
    public ColumnsDefinition deserialize() {
        try {
            String columnsDefinitionString = Files.readString(path);
            return ColumnsDefinition.fromString(columnsDefinitionString);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
