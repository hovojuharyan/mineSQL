package just.fun.defaults.fileio;

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

import static just.fun.defaults.fileio.Defaults.ROOT_PATH;

public class FileMetadataSerializer implements MetadataSerializer {

    @Override
    public void serialize(String tableName, Metadata content) {
        try {
            Files.writeString(Path.of(ROOT_PATH + "tbl_metadata_" + tableName + ".txt"), content.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Metadata deserialize(String tableName) {
        Scanner scanner = new Scanner(ROOT_PATH + "tbl_metadata_" + tableName + ".txt");
        String name = scanner.nextLine();
        List<Column<Object>> columnList = Arrays.stream(scanner.nextLine().split("\\|"))
                .map(colStr -> (Column<Object>) new Column<>(colStr.split("-")[0],
                        ColumnType.of(colStr.split("-")[1])))
                .toList();
        Columns columns = new Columns(columnList);
        LocalDateTime createdAt = LocalDateTime.parse(scanner.nextLine());
        return new Metadata(name, columns, createdAt);
    }
}
