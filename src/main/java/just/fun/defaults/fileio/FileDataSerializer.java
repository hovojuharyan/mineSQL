package just.fun.defaults.fileio;

import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.serialization.DataSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static just.fun.defaults.fileio.Defaults.ROOT_PATH;

public class FileDataSerializer implements DataSerializer {

    private final FileMetadataSerializer fileMetadataSerializer;

    public FileDataSerializer(FileMetadataSerializer fileMetadataSerializer) {
        this.fileMetadataSerializer = fileMetadataSerializer;
    }

    @Override
    public void serialize(String tableName, Data content) {
        try {
            Files.writeString(Path.of(ROOT_PATH + "tbl_data_" + tableName + ".txt"), content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Data deserialize(String tableName) {
        try {
            Metadata metadata = fileMetadataSerializer.deserialize(tableName);
            Columns columns = metadata.columns();
            List<Row> rowList = Files.readAllLines(Path.of(ROOT_PATH + "tbl_data_" + tableName + ".txt"))
                    .stream()
                    .map(rowStr -> Row.initRow(columns, List.of(rowStr.split("-"))))
                    .toList();
            return new Data(rowList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
