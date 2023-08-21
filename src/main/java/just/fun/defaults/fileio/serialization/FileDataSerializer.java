package just.fun.defaults.fileio.serialization;

import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;
import just.fun.serialization.DataSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static just.fun.defaults.fileio.serialization.DefaultFileSerialization.ROOT_PATH;

public class FileDataSerializer implements DataSerializer {

    private final String path;
    private final Metadata metadata;

    public FileDataSerializer(String path, FileMetadataSerializer fileMetadataSerializer) {
        this.path = ROOT_PATH + "tbl_data_" + path + ".txt";
        this.metadata = fileMetadataSerializer.deserialize();
    }

    @Override
    public void serialize(Data content) {
        try {
            Files.writeString(Path.of(path), content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Data deserialize() {
        try {
            Columns columns = metadata.columns();
            List<Row> rowList = Files.readAllLines(Path.of(path))
                    .stream()
                    .map(rowStr -> Row.initRow(columns, List.of(rowStr.split("-"))))
                    .toList();
            return new Data(rowList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
