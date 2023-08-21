package just.fun.defaults.fileio.serialization;

import just.fun.domain.schema.Column;
import just.fun.domain.schema.Columns;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.types.ColumnType;
import just.fun.serialization.MetadataSerializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static just.fun.defaults.fileio.serialization.DefaultFileSerialization.ROOT_PATH;

public class FileMetadataSerializer implements MetadataSerializer {

    private final Path path;

    public FileMetadataSerializer(String tableName) {
        this.path = Path.of(ROOT_PATH + "tbl_metadata_" + tableName + ".txt");
    }

    @Override
    public void serialize(Metadata content) {
        try {
            Files.writeString(path, content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Metadata deserialize() {
        try (Scanner scanner = new Scanner(path)) {
            String tableName = scanner.nextLine();
            List<Column<Object>> columnList = Arrays.stream(scanner.nextLine().split("\\|"))
                    .map(colStr -> (Column<Object>) new Column<>(colStr.split("-")[0],
                            ColumnType.of(colStr.split("-")[1])))
                    .toList();
            Columns columns = new Columns(columnList);
            LocalDateTime createdAt = LocalDateTime.parse(scanner.nextLine());
            return new Metadata(tableName, columns, createdAt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
