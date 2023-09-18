package just.fun.defaults.fileio;

import just.fun.domain.schema.Row;
import just.fun.serialization.RowSerializer;

import java.io.FileWriter;
import java.io.IOException;

import static just.fun.defaults.fileio.Defaults.ROOT_PATH;

public class FileRowSerializer implements RowSerializer {

    @Override
    public void serialize(String tableName, Row row) {
        try (FileWriter fileWriter = new FileWriter(ROOT_PATH + "tbl_data_" + tableName + ".txt", true)) {
            fileWriter.append(row.toString()).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
