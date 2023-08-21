package just.fun.defaults.fileio.serialization;

import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;

import java.io.FileWriter;
import java.io.IOException;

import static just.fun.defaults.fileio.serialization.DefaultFileSerialization.ROOT_PATH;

public class FileRowSerializer implements RowSerializer {

    private final String path;

    public FileRowSerializer(String tableName) {
        this.path = ROOT_PATH + "tbl_data_" + tableName + ".txt";
    }

    @Override
    public void serialize(Row row) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.append(row.toString()).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
