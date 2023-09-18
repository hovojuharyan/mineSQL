package just.fun.domain.assertions;

import just.fun.domain.schema.Data;
import just.fun.domain.schema.Metadata;
import just.fun.domain.schema.Row;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileAssertions {

    public static final String ROOT_PATH = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";

    private FileAssertions() {
    }

    public static void assertMetadataFileExists(String tableName) {
        assert Files.exists(Path.of(metadataPath(tableName))) : "Metadata file for " + tableName + " is not present";
    }

    public static void assertDataFileExists(String tableName) {
        assert Files.exists(Path.of(dataPath(tableName))) : "Data file for " + tableName + " is not present";
    }

    public static void assertContainsMetadata(String tableName, Metadata metadata) throws IOException {
        assert Files.readString(Path.of(metadataPath(tableName))).equals(metadata.toString());
    }

    public static void assertContainsData(String tableName, Data data) throws IOException {
        assert Files.readString(Path.of(dataPath(tableName))).equals(data.toString());
    }

    public static void assertContainsRow(String tableName, Row row) throws IOException {
        assert Files.readString(Path.of(dataPath(tableName))).contains(row.toString());
    }

    private static String metadataPath(String tableName) {
        return ROOT_PATH + "tbl_metadata_" + tableName + ".txt";
    }

    private static String dataPath(String tableName) {
        return ROOT_PATH + "tbl_data_" + tableName + ".txt";
    }
}
